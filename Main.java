package a2_WassimDakka_40276235;

import java.io.*;
import java.util.Scanner;

public class Main {

    // Define arrays containing valid ratings and genres
    private static final String[] VALID_RATINGS = {"PG", "Unrated", "G", "R", "PG-13", "NC-17"};
    private static final String[] VALID_GENRES = {"musical", "comedy", "animation", "adventure", "drama", "crime",
            "biography", "horror", "action", "documentary", "fantasy", "mystery", "sci-fi", "family", "romance",
            "thriller", "western"};
    private static int currentGenreIndex;
    private static int currentPosition;


    public static boolean isValidRating(String rating) {
        for (String validRating : VALID_RATINGS) {
            if (validRating.equals(rating)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidGenre(String genre) {
        for (String validGenre : VALID_GENRES) {
            if (validGenre.equalsIgnoreCase(genre)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String part1Manifest = "/Users/wassimdakka/Downloads/part1_manifest.txt";
        String part2Manifest = do_part1(part1Manifest);
        String part3Manifest =  String.valueOf(doPart3(part2Manifest)); 

        // Deserialize the movie arrays
        Movie[][] allMovies = doPart3(part3Manifest);
        navigateMovies(allMovies);
    }

    public static String do_part1(String part1Manifest) {
        try {
            BufferedReader manifestReader = new BufferedReader(new FileReader(part1Manifest));
            String inputFile;
            while ((inputFile = manifestReader.readLine()) != null) {
                processInputFile(inputFile);
            }
            manifestReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the path to part 2 manifest
        return "/Users/wassimdakka/Downloads/part2_manifest.txt";
    }

    private static void processInputFile(String inputFile) {
        try {
            BufferedReader inputFileReader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = inputFileReader.readLine()) != null) {
                validateAndPartitionMovieRecord(line);
            }
            inputFileReader.close();
        } catch (IOException e) {
            System.err.println("Error reading input file: " + inputFile);
            e.printStackTrace();
        }
    }

    
    private static void validateAndPartitionMovieRecord(String record) {
        // Split the record into individual fields using the comma as the delimiter
        String[] fields = record.split(",");

        // Check if the number of fields is exactly 10
        if (fields.length != 10) {
            writeErrorToBadRecordsFile("ExcessFieldsException", record);
            return;
        }

        // Extract and validate each field
        String yearStr = fields[0];
        String title = fields[1];
        String durationStr = fields[2];
        String genres = fields[3];
        String rating = fields[4];
        String scoreStr = fields[5];
        String director = fields[6];
        String actor1 = fields[7];
        String actor2 = fields[8];
        String actor3 = fields[9];

        try {
            int year = Integer.parseInt(yearStr);
            if (year < 1990 || year > 1999) {
                throw new IllegalArgumentException("Invalid year");
            }

            int duration = Integer.parseInt(durationStr);
            if (duration < 30 || duration > 300) {
                throw new IllegalArgumentException("Invalid duration");
            }

            double score = Double.parseDouble(scoreStr);
            if (score < 0 || score > 10) {
                throw new IllegalArgumentException("Invalid score");
            }

            // Validate the rating field
            if (!isValidRating(rating)) {
                throw new IllegalArgumentException("Invalid rating: " + rating);
            }

            // Validate the genre field
            if (!isValidGenre(genres)) {
                throw new IllegalArgumentException("Invalid genre: " + genres);
            }

            // Write the record to the corresponding genre CSV file
            partitionRecordToGenreFile(genres, record);
        } catch (IllegalArgumentException e) {
            // Handle semantic error: Invalid numeric format for year, duration, or score
            writeErrorToBadRecordsFile("Semantic error: " + e.getMessage(), record);
        }
    }

    private static void writeErrorToBadRecordsFile(String error, String record) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("bad_movie_records.txt", true))) {
            writer.println(error);
            writer.println("Record: " + record);
            writer.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void partitionRecordToGenreFile(String genre, String record) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(genre + ".csv", true))) {
            writer.println(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String do_part2(String part2Manifest) {
        // Process part 2's manifest file and return the path to part 3's manifest
        return "/Users/wassimdakka/Downloads/part3_manifest.txt";
    }

 

    public static Movie[][] doPart3(String part3Manifest) {
        // Read the part3_manifest.txt file to get the names of the binary files
        String manifestFile = part3Manifest;
        Movie[][] allMovies = new Movie[VALID_GENRES.length][];

        try (BufferedReader manifestReader = new BufferedReader(new FileReader(manifestFile))) {
            String line;
            int index = 0;
            while ((line = manifestReader.readLine()) != null) {
                String fileName = line.trim();
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                allMovies[index] = (Movie[]) in.readObject();
                in.close();
                index++;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return allMovies;
    }

    public static void navigateMovies(Movie[][] allMovies) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("----------------------------");
            System.out.println("Main Menu");
            System.out.println("----------------------------");
            System.out.println("s Select a movie array to navigate");
            System.out.println("x Exit");
            System.out.println("----------------------------");
            System.out.print("Enter Your Choice: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("s")) {
                // Display the Genre Sub-Menu
                System.out.println("----------------------------");
                System.out.println("Genre Sub-Menu");
                System.out.println("----------------------------");
                for (int i = 0; i < VALID_GENRES.length; i++) {
                    int count = allMovies[i] != null ? allMovies[i].length : 0;
                    System.out.println((i + 1) + " " + VALID_GENRES[i] + " (" + count + " movies)");
                    System.out.println((VALID_GENRES.length + 1) + " Exit");
                    System.out.println("----------------------------");
                    System.out.print("Enter Your Choice: ");
                    int genreChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (genreChoice == VALID_GENRES.length + 1) {
                        continue;
                    }

                    if (genreChoice >= 1 && genreChoice <= VALID_GENRES.length) {
                        currentGenreIndex = genreChoice - 1;
                        currentPosition = 0;
                    }
                }
            } else if (choice.equalsIgnoreCase("x")) {
                System.out.println("Exiting...");
                break;
            }

            // Navigate the selected movie array
            navigateGenre(allMovies[currentGenreIndex], currentPosition, scanner);

        }
        scanner.close();
    }

    public static void navigateGenre(Movie[] movies, int currentPosition, Scanner scanner) {
        while (true) {
            System.out.println("Navigating " + VALID_GENRES[currentGenreIndex] + " movies (" + movies.length + " records)");
            System.out.println("Enter Your Choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 0) {
                System.out.println("Viewing session ends.");
                break;
            }

            if (choice < 0) {
                // Display records above the current position
                int startIndex = Math.max(0, currentPosition + choice - 1);
                int endIndex = currentPosition - 1;
                if (startIndex == 0) {
                    System.out.println("BOF has been reached.");
                }
                for (int i = startIndex; i <= endIndex; i++) {
                    if (i < movies.length) {
                        displayMovie(movies[i]);
                    }
                }
                currentPosition = startIndex;
            } else if (choice > 0) {
                // Display records below the current position
                int startIndex = currentPosition + choice;
                int endIndex = Math.min(movies.length - 1, startIndex + Math.abs(choice) - 1);
                if (endIndex == movies.length - 1) {
                    System.out.println("EOF has been reached.");
                }
                for (int i = startIndex; i <= endIndex; i++) {
                    if (i < movies.length) {
                        displayMovie(movies[i]);
                    }
                }
                currentPosition = endIndex;
            }
        }
    }

    public static void displayMovie(Movie movie) {
        System.out.println("Year: " + movie.getYear());
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Duration: " + movie.getDuration());
        System.out.println("Genres: " + movie.getGenres());
        System.out.println("Rating: " + movie.getRating());
        System.out.println("Score: " + movie.getScore());
        System.out.println("Director: " + movie.getDirector());
        System.out.println("Actor1: " + movie.getActor1());
        System.out.println("Actor2: " + movie.getActor2());
        System.out.println("Actor3: " + movie.getActor3());
        System.out.println("----------------------------------");
    }
}

               

	

	


