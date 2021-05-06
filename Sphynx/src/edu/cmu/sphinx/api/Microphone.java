/*
 * Copyright 1999-2004 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 *
 */
package edu.cmu.sphinx.api;

import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

/**
 * InputStream adapter
 */
public class Microphone {
   private final TargetDataLine line;
   private final InputStream inputStream;

   public Microphone(float sampleRate, int sampleSize, boolean signed, boolean bigEndian) {
      AudioFormat format = new AudioFormat(sampleRate, sampleSize, 1, signed, bigEndian);
      try {
         line = AudioSystem.getTargetDataLine(format);
         line.open();
      } catch (IllegalArgumentException | LineUnavailableException e) {
         throw new MicrophoneUnavailableException(e);         
      }
      inputStream = new AudioInputStream(line);
   }

   public void startRecording() {
      line.start();
   }

   public void stopRecording() {
      line.stop();
   }

   public void closeConnection() {
      line.close();
   }

   public InputStream getStream() {
      return inputStream;
   }
}
