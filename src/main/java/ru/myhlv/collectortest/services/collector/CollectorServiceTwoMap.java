package ru.myhlv.collectortest.services.collector;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.myhlv.collectortest.brokers.CollectorResultTwoMap;
import ru.myhlv.collectortest.converters.InputStringConverter;
import ru.myhlv.collectortest.entyties.Group;
import ru.myhlv.collectortest.entyties.InputString;
import ru.myhlv.collectortest.inputdata.InputDataFile;
import ru.myhlv.collectortest.utils.FileUtils;
import ru.myhlv.collectortest.utils.Informer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


@Service
public class CollectorServiceTwoMap implements CollectorService {

    private final Map<String, Group> tempMap = new HashMap<>();

    private final CollectorResultTwoMap resultMap;
    private final InputDataFile inputData;
    private final InputStringConverter converter;
    private final Informer informer;

    public CollectorServiceTwoMap(@NonNull final CollectorResultTwoMap resultMap,
                                  @NonNull final InputDataFile inputData,
                                  @NonNull final InputStringConverter converter,
                                  @NonNull final Informer informer) {
        this.resultMap = resultMap;
        this.inputData = inputData;
        this.converter = converter;
        this.informer = informer;
    }

    public void collectStrings() throws IOException {
        val file = inputData.getData();
        checkFile(file);

        try (val br = new BufferedReader(new FileReader(inputData.getData().toFile()))) {
            while (br.ready()) {
                final InputString inputString = converter.getModel(br.readLine());
                informer.incStringsCounter();
                final List<String> values = inputString.getValues();
                final HashSet<Group> valuesOwners = getValuesOwners(values);
                distributeString(valuesOwners, inputString);
            }
        } catch (IOException e) {
            throw new IOException(e.getCause());
        }
    }

    private void checkFile(@NonNull final Path file) throws FileNotFoundException {
        if (!FileUtils.fileIsValide(file)) {
            throw new FileNotFoundException("Wrong file");
        }
    }

    private HashSet<Group> getValuesOwners(@NonNull final List<String> values) {
        val valuesOwners = new HashSet<Group>();
        for (val value : values) {
            if (value.equals("\"\"")) {
                continue;
            }
            val valueOwner = getValueOwner(value);
            if (valueOwner != null) {
                valuesOwners.add(valueOwner);
            }
        }
        return valuesOwners;
    }

    private void distributeString(@NonNull final Set<Group> valuesOwners,
                                  @NonNull final InputString inputString) {
        if (valuesOwners.isEmpty()) {
            addNewValuesAndGroup(inputString);
        } else {
            mergeGroups(valuesOwners, inputString);
        }
    }

    private void addNewValuesAndGroup(@NonNull final InputString inputString) {
        val newGroup = Group.of(new ArrayList<>(List.of(inputString)));
        setNewOwnerForValues(newGroup, inputString.getValues());
    }

    private void mergeGroups(@NonNull final Set<Group> valuesOwners,
                             @NonNull final InputString inputString) {
        val newAlikeInputStringList = new ArrayList<>(List.of(inputString));
        val newValuesSet = new HashSet<String>(inputString.getValues());
        for (final Group valuesOwner : valuesOwners) {
            if (valuesOwner == null) {
                continue;
            }
            newAlikeInputStringList.addAll(valuesOwner.getAlikeStrings());
            newValuesSet.addAll(resultMap.get(valuesOwner));
        }
        val newGroup = Group.of(newAlikeInputStringList);
        setNewOwnerForValues(newGroup, newValuesSet);
        removeAllOldGroups(valuesOwners);
    }

    private void setNewOwnerForValues(@NonNull final Group newGroup,
                                      @NonNull final Collection<String> values) {
        for (final String string : values) {
            if (string.equals("\"\"")) {
                continue;
            }
            tempMap.put(string, newGroup);
        }
        resultMap.put(newGroup, Set.copyOf(values));
    }

    private void removeAllOldGroups(@NonNull final Set<Group> valuesOwners) {
        for (final Group valuesOwner : valuesOwners) {
            resultMap.remove(valuesOwner);
        }
    }

    private Group getValueOwner(@NonNull final String value) {
        return tempMap.get(value);
    }
}
