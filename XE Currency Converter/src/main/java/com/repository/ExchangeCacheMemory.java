package repository;

import domain.external.CacheMemory;
import helper.ExchangePair;

import java.util.*;

import static helper.DataTimeHelper.getCurrentTimeAsString;

public class ExchangeCacheMemory implements CacheMemory<ExchangePair, String> {

    private Map<String, Map<String, List<String>>> memory = new HashMap<>();
    private boolean hasCurrencyFrom;
    private int durationOfValidityInSeconds = 120;

    @Override
    public boolean isMemoryExpired(ExchangePair key) {
        if (this.memory.isEmpty()) {
            return true;
        }

        if (this.memory.containsKey(key.getFromCurrency())) {
            hasCurrencyFrom = true;
        } else {
            hasCurrencyFrom = false;
            return true;
        }

        Map<String, List<String>> currencyToMap = this.memory.get(key.getFromCurrency());
        if (!currencyToMap.containsKey(key.getToCurrency())) {
            return true;
        }

        int lastTimeToUpdate = 1;

        long timeFromMemory = Long.parseLong(this.getInfoFrom(key, lastTimeToUpdate));

        long currentTime = Long.parseLong(getCurrentTimeAsString());

        if (timeFromMemory + durationOfValidityInSeconds > currentTime) {
            return false;
        }

        return true;
    }

    @Override
    public String getValue(ExchangePair key) {
        return getInfoFrom(key, 0);
    }

    private String getInfoFrom(ExchangePair key, int index) {
        return this.memory.get(key.getFromCurrency()).get(key.getToCurrency()).get(index);
    }

    @Override
    public void putValue(ExchangePair key, String value) {

        List<String> valueToInnerMap = new ArrayList<>();
        valueToInnerMap.add(value);
        valueToInnerMap.add(getCurrentTimeAsString());

        if (hasCurrencyFrom) {
            Map<String, List<String>> currencyToMap = this.memory.get(key.getFromCurrency());
            currencyToMap.put(key.getToCurrency(), valueToInnerMap);
        } else {
            Map<String, List<String>> innerMap = new HashMap<>();
            innerMap.put(key.getToCurrency(), valueToInnerMap);

            this.memory.put(key.getFromCurrency(), innerMap);
        }
    }

    public void setDurationOfValidityInSeconds(int durationOfValidityInSeconds) {
        this.durationOfValidityInSeconds = durationOfValidityInSeconds;
    }
}
