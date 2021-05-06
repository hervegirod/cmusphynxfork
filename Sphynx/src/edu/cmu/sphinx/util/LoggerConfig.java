package edu.cmu.sphinx.util;

import java.util.logging.Logger;

/**
 *
 * @author scdsahv
 */
public class LoggerConfig {
   private static Logger LOGGER = null;

   public static final Logger getLogger() {
      if (LOGGER == null) {
         LOGGER = Logger.getLogger(LoggerConfig.class.getSimpleName());
      }
      return LOGGER;
   }
}
