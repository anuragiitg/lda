/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movielens;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author kaldr
 */
public class SampleData {

    ArrayList users;
    ArrayList movies;

    public SampleData() {
        movies = new ArrayList();
        users = new ArrayList();
    }

    public static void main(String args[]) {
        SampleData sampledata = new SampleData();
        sampledata.SampleUser("userwithmovies.dat");
        sampledata.CheckMovieAmount(sampledata);
        sampledata.SampleTags("tags.dat");
    }

    public void CheckMovieAmount(SampleData sampledata) {
        try {
            System.out.println("Start Checking...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Sampled/userwithmovies.dat"), "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer tknz = new StringTokenizer(line, " ");
                String user = tknz.nextToken();
                if (!tknz.hasMoreTokens()) {
                    System.out.println("Not correct, resampling...");
                    sampledata.SampleUser("userwithmovies.dat");
                    sampledata.CheckMovieAmount(sampledata);
                    break;
                }
                line = reader.readLine();
            }
            System.out.println("Totally correct.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SampleUser(String filename) {
        try {
            System.out.println("Start Sampling user...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
            BufferedReader Moviereader = new BufferedReader(new InputStreamReader(new FileInputStream("movies.dat"), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Sampled/userwithmovies.dat"), "UTF-8"));
            BufferedWriter Moviewriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Sampled/movies.dat"), "UTF-8"));
            String line = reader.readLine();
            String movieline = Moviereader.readLine();
            while (movieline != null) {
                double randnum = Math.random();
                if (randnum < 0.3) {
                    StringTokenizer tknz = new StringTokenizer(movieline, "::");
                    String movie = tknz.nextToken();
                    movies.add(movie);

                    Moviewriter.write(movieline + "\r\n");
                }
                movieline = Moviereader.readLine();
            }
            while (line != null) {
                double randnum = Math.random();
                if (randnum < 0.05) {
                    StringTokenizer tknz = new StringTokenizer(line, " ");
                    String user = tknz.nextToken();
                    users.add(user);
                    writer.write(user + " ");
                    while (tknz.hasMoreTokens()) {
                        String movie = tknz.nextToken();
                        if (movies.contains(movie)) {
                            writer.write(movie + " ");
                        }
                    }
                    writer.write("\r\n");
                }
                line = reader.readLine();
            }
            Collections.sort(movies);
            reader.close();
            writer.close();
            System.out.println("Sampled");
            Collections.sort(users);
            Collections.sort(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SampleTags(String filename) {
        try {
            System.out.println("Start Sampling tags...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Sampled/tags.dat"), "UTF-8"));
            String line=reader.readLine();
            StringTokenizer tknz;
            String user;
            String lastuser;
            String movie;
            while(line!=null){
                tknz=new StringTokenizer(line,"::");
                user=tknz.nextToken();
                movie=tknz.nextToken();
                
                lastuser=user;
            }
            reader.close();
            writer.close();
            System.out.println("Sampled");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}