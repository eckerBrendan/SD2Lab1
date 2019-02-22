/*
 * Course: CS1011 - 051
 * Winter 2018
 * Lab 1 - Wav Files
 * Name: Brendan Ecker
 * Created: 12/4/2018
 */package eckerb;

import edu.msoe.taylor.audio.WavFile;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This lab is asking the user to enter a choice of options
 * then running the option they chose and running that code.
 *
 * For option 2: ends the program. Then prompts the user
 * for another choice/
 *
 * For option 1: asks the user to enter a file without wav
 * and reverse the wav file.
 * Then prompts the user for another choice.
 *
 * For option 2: asks the user for a wavFile without the .wav
 * and a frequency to play it at.
 * Then takes that file and sets the frequency to what was input
 * and makes a 1 second file of that. Then prompts the user
 * for another choice.
 *
 * For option 3: it outprints "Did not implement!"
 * and prompts the user for a choice.
 */
public class Lab1 {
    private static String option;
    private static Scanner in = new Scanner(System.in);
    private static int numChannels;
    private static long numFrames;
    private static int validBits;
    private static long sampleRate;
    private static ArrayList<Double> samples = new ArrayList<>();
    private static ArrayList<Double> samplesRev = new ArrayList<>();

    public static void main(String[] args) {
        prompt();
    }

    private static void prompt() {
        do {
            System.out.println("Please enter 0,1,2,3 for your option:");
            option = in.next();
        } while ((option.equals("0")) &&
                (option.equals("1")) &&
                (option.equals("2")) &&
                (option.equals("2")));
        choice();
    }

    /**
     * Uses the choice the user chose in prompt and runs
     * the code for that choice.
     */
    private static void choice() {
        if (option.equals("0")) {
            System.out.println(" ");
        } else if (option.equals("1")) {
            option1();
        } else if (option.equals("2")) {
            option2();
        } else if (option.equals("3")) {
            option3();
        }
    }

    private static void option1() {
        System.out.println("Please enter a wav file without the .wav extension:");
        String fileName = in.next();
        WavFile wavFile = new WavFile(fileName +".wav");
        sampleRate = wavFile.getSampleRate();
        numChannels = wavFile.getNumChannels();
        numFrames = wavFile.getNumFrames();
        validBits = wavFile.getValidBits();
        samples = wavFile.getSamples();
        wavFile.close();
        System.out.println(wavFile.toString());
        String fileName2 = fileName + "Rev.wav";
        WavFile revFile = new WavFile(fileName2, numChannels, numFrames, validBits, sampleRate);
        for(int i = 0; i<samples.size(); i++){
            samplesRev.add(samples.get(samples.size() - (i +1)));
        }
        revFile.setSamples(samplesRev);
        revFile.close();
        prompt();
    }

    private static void option2() {
        System.out.println("Please enter a file name without the .wav extension:");
        String wavFile = in.next();
        System.out.println("Please enter the Frequency you want the file to be in:");
        int frequency = in.nextInt();

        String fileName3 = wavFile + ".wav";
        WavFile sound = new WavFile(fileName3);
        numChannels = sound.getNumChannels();
        numFrames = sound.getNumFrames();
        sampleRate = sound.getSampleRate();
        validBits = sound.getValidBits();
        ArrayList<Double> soundFreq = getFrequency(frequency, sound);
        String fileName4 = "Tone " + fileName3;
        WavFile freqWav = new WavFile(fileName4, numChannels, numFrames, validBits, sampleRate);
        freqWav.setSamples(soundFreq);
        freqWav.close();
        prompt();
    }

    private static ArrayList<Double> getFrequency(int frequency, WavFile freq2){
        ArrayList<Double> newTone = new ArrayList<>();
        ArrayList<Double> samples2 = freq2.getSamples();
        for(int i = 0; i < samples2.size(); i++){
            newTone.add(Math.sin(2 * Math.PI *i* frequency / freq2.getSampleRate()));
        }
        return newTone;
    }

    private static void option3() {
        System.out.println("Did not implement!");
        prompt();
    }
}

