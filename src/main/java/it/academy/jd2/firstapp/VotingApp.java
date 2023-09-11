package it.academy.jd2.firstapp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class VotingApp {
    private final List<VotingForm> votingForms;

    public VotingApp() {
        this.votingForms = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Создать новое голосование");
            System.out.println("2 - Посмотреть результаты голосования");
            System.out.println("0 - Выход");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createVotingForm(scanner);
                    break;
                case 2:
                    printVotingResults();
                    break;
                case 0:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Некорректный ввод, попробуйте снова.");
                    break;
            }
            System.out.println();
        }
    }

    private void createVotingForm(Scanner scanner) {
        System.out.println("\n--- Создание нового голосования ---");

        System.out.print("Введите лучшего исполнителя (выберите 1 из 4 вариантов): ");
        String bestPerformer = scanner.next();

        System.out.print("Введите ваши любимые жанры (выберите 3-5 вариантов из 10): ");
        List<String> favoriteGenres = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            favoriteGenres.add(scanner.next());
        }

        System.out.print("Введите краткий текст о себе: ");
        scanner.nextLine(); // consume the remaining newline character
        String aboutYou = scanner.nextLine();

        VotingForm form = new VotingForm(List.of(bestPerformer), favoriteGenres, aboutYou);
        votingForms.add(form);

        System.out.println("Голосование успешно отправлено!\n");
    }

    private void printVotingResults() {
        if (votingForms.isEmpty()) {
            System.out.println("\nРезультаты голосования отсутствуют.\n");
            return;
        }

        System.out.println("\n--- Результаты голосования ---");

        List<String> bestPerformers = new ArrayList<>();
        List<String> genres = new ArrayList<>();
        for (VotingForm form : votingForms) {
            bestPerformers.addAll(form.getBestPerformers());
            genres.addAll(form.getFavoriteGenres());
        }

        System.out.println("Список Лучших исполнителей (отсортирован по количеству голосов):");
        bestPerformers.stream()
                .distinct()
                .sorted(Comparator.comparingInt(v -> getCount(bestPerformers, v)))
                .forEach(v -> System.out.println(v + " - " + getCount(bestPerformers, v) + " голосов"));

        System.out.println("\nСписок Любимых жанров (отсортирован по количеству голосов):");
        genres.stream()
                .distinct()
                .sorted(Comparator.comparingInt(g -> getCount(genres, g)))
                .forEach(g -> System.out.println(g + " - " + getCount(genres, g) + " голосов"));

        System.out.println("\nСписок отправленных голосований (отсортирован по дате/времени отправки):");
        votingForms.stream()
                .sorted(Comparator.comparing(VotingForm::getTimestamp))
                .forEach(form -> {
                    System.out.println("Дата/время отправки: " + form.getTimestamp());
                    System.out.println("Краткий текст о себе: " + form.getAboutYou());
                    System.out.println();
                });

        System.out.println();
    }

    private int getCount(List<String> list, String value) {
        return (int) list.stream()
                .filter(v -> v.equals(value))
                .count();
    }
}
