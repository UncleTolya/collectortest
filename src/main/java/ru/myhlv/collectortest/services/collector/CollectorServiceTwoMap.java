package ru.myhlv.collectortest.services.collector;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.myhlv.collectortest.brokers.CollectorResultMap;
import ru.myhlv.collectortest.converters.InputStringConverter;
import ru.myhlv.collectortest.entyties.Group;
import ru.myhlv.collectortest.entyties.InputString;
import ru.myhlv.collectortest.io.InputDataFile;
import ru.myhlv.collectortest.utils.ConsoleUtils;
import ru.myhlv.collectortest.utils.FileUtils;
import ru.myhlv.collectortest.services.CollectorInformer;

import java.io.*;
import java.util.*;


@Service
public class CollectorServiceTwoMap implements CollectorService {

    private final Map<String, Group> tempMap = new HashMap<>();

    private final CollectorResultMap resultMap;
    private final InputDataFile inputData;
    private final InputStringConverter converter;
    private final CollectorInformer informer;

    public CollectorServiceTwoMap(@NonNull final CollectorResultMap resultMap,
                                  @NonNull final InputDataFile inputData,
                                  @NonNull final InputStringConverter converter,
                                  @NonNull final CollectorInformer informer) {
        this.resultMap = resultMap;
        this.inputData = inputData;
        this.converter = converter;
        this.informer = informer;
    }

    public void collectStrings() throws IOException {
        try (val br = new BufferedReader(new FileReader(checkAndGetFile()))) {
            while (br.ready()) {
                final InputString inputString = parseInputString(br.readLine());
                if (inputString == null) {
                    continue;
                }
                informer.incStringsCounter();
                final List<String> values = inputString.getValues();
                final HashSet<Group> valuesOwners = getValuesOwners(values);
                distributeString(valuesOwners, inputString);
            }
        }
    }

    private InputString parseInputString(@NonNull final String string) {
        final InputString inputString;
        try {
            inputString = converter.getModel(string);
        } catch (IllegalArgumentException e) {
            ConsoleUtils.log(e.getMessage() + " " + string);
            informer.addInvalidString(string);
            return null;
        }
        return inputString;
    }

    private File checkAndGetFile() throws FileNotFoundException {
        val pathToData = inputData.getPath();
        if (!FileUtils.fileIsValid(pathToData)) {
            throw new FileNotFoundException("Wrong input file name");
        }
        return pathToData.toFile();
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
