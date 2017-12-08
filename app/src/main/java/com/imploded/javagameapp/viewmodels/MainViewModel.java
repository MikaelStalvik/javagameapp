package com.imploded.javagameapp.viewmodels;

import com.imploded.javagameapp.models.FilterItem;
import com.imploded.javagameapp.models.Game;
import com.imploded.javagameapp.repository.MainRepository;
import com.imploded.javagameapp.utils.AppConstants;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainViewModel {

    private List<Game> allGames;
    private List<Game> activeGames;

    public List<Game> getGamesForView() {
        switch(activeSorting) {
            case AppConstants.SortingNameId:
                return gamesSortedByName();
            case AppConstants.SortingPublisherId:
                return gamesSortedByPublisher();
            case AppConstants.SortingReleaseYearId:
                return gamsSortedByReleaseYear();
        }
        return allGames;
    }

    public String activeSorting = AppConstants.SortingNameId;
    public String activeFilter = "";

    public boolean ascending = true;

    public Game getGame(int position) {
        return activeGames.get(position);
    }

    public void getGamesFromServer() throws ExecutionException, InterruptedException {
        allGames = new MainRepository().getGames();
        activeGames = allGames;
    }

    public Map<String, Integer> getAllPlatforms() {
        Map<String, Integer> result = new HashMap<String, Integer>();

        for (Game game : allGames) {
            for(String platform : game.getPlatforms()) {
                if (result.containsKey(platform)) {
                    int count = result.get(platform) + 1;
                    result.put(platform, count);
                }
                else {
                    result.put(platform, 1);
                }
            }
        }

        return result;
    }

    private void updateSorting(String newSort) {
        String oldSort = activeSorting;
        activeSorting = newSort;
        if (oldSort.equals(activeSorting)) {
            ascending = !ascending;
        }
        else {
            ascending = true;
        }
    }
    private List<Game> gamesSortedByName() {
        updateSorting(AppConstants.SortingNameId);
        Collections.sort(allGames,new Comparator<Game>() {
            @Override
            public int compare(Game a, Game b) {
                if (ascending) {
                    return b.getName().compareTo(a.getName());
                }
                else {
                    int res = b.getName().compareTo(a.getName());
                    return -res;
                }
            }
        });
        return allGames;
    }
    private List<Game> gamesSortedByPublisher() {
        updateSorting(AppConstants.SortingPublisherId);
        Collections.sort(allGames,new Comparator<Game>() {
            @Override
            public int compare(Game a, Game b) {
                if (ascending) {
                    return b.getPublisher().compareTo(a.getPublisher());
                }
                else {
                    int res = b.getPublisher().compareTo(a.getPublisher());
                    return -res;
                }
            }
        });
        return allGames;
    }
    private List<Game> gamsSortedByReleaseYear() {
        updateSorting(AppConstants.SortingReleaseYearId);
        Collections.sort(allGames,new Comparator<Game>() {
            @Override
            public int compare(Game a, Game b) {
                if (ascending) {
                    return b.getReleaseYear().compareTo(a.getReleaseYear());
                }
                else {
                    int res = b.getReleaseYear().compareTo(a.getReleaseYear());
                    return -res;
                }
            }
        });
        return allGames;
    }

    private void updateFilter() {
        if (activeFilter.isEmpty()) {
            activeGames = allGames;
            return;
        }

    }

}
