package it.academy.jd2.firstapp;

import java.util.Date;
import java.util.List;

public class VotingForm {
    private final List<String> bestPerformers;
    private final List<String> favoriteGenres;
    private final String aboutYou;
    private final Date timestamp;

    public VotingForm(List<String> bestPerformers, List<String> favoriteGenres, String aboutYou) {
        this.bestPerformers = bestPerformers;
        this.favoriteGenres = favoriteGenres;
        this.aboutYou = aboutYou;
        this.timestamp = new Date();
    }

    public List<String> getBestPerformers() {
        return bestPerformers;
    }

    public List<String> getFavoriteGenres() {
        return favoriteGenres;
    }

    public String getAboutYou() {
        return aboutYou;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
