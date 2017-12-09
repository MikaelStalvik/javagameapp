package com.imploded.javagameapp.viewmodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imploded.javagameapp.models.FilterItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FilterViewModel {

    public List<FilterItem> filterItems;

    private List<String> getActiveFilters(String activeFilter, List<String> allPlatforms) {
        if (activeFilter.isEmpty()) {
            return allPlatforms;
        }
        List<String> result = new ArrayList<>();
        Type filterType = new TypeToken<List<FilterItem>>(){}.getType();
        List<FilterItem> activeFilters = new Gson().fromJson(activeFilter, filterType);
        for(FilterItem item : activeFilters) {
            if (item.checked) {
                result.add(item.name);
            }
        }
        return result;
    }

    public void restoreActiveFilters(String allPlatformsJson, String activeFilterJson) {
        Gson gson = new Gson();
        Type platformType = new TypeToken<Map<String, Integer>>(){}.getType();
        Map<String, Integer> allPlatforms = gson.fromJson(allPlatformsJson, platformType);
        List<String> allPlatformNames = new ArrayList<>();
        allPlatformNames.addAll(allPlatforms.keySet());
        List<String> activeFilters = getActiveFilters(activeFilterJson, allPlatformNames);

        filterItems = new ArrayList<>();
        for(String key : allPlatforms.keySet()) {
            FilterItem item = new FilterItem();
            item.name = key;
            item.checked = activeFilters.contains(key);
            item.count = allPlatforms.get(key);
            filterItems.add(item);
        }
        Collections.sort(filterItems, new Comparator<FilterItem>() {
            @Override
            public int compare(FilterItem o1, FilterItem o2) {
                return o1.name.compareTo(o2.name);
            }
        });
    }

    public String getFilterItemsAsJson() {
        Gson gson = new Gson();
        return gson.toJson(filterItems);
    }

    public void selectAll() {
        for(FilterItem item : filterItems) {
            item.checked = true;
        }
    }
    public void selectNone() {
        for(FilterItem item : filterItems) {
            item.checked = false;
        }
    }
}
