/*
 *
 * Copyright 2016 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.github.wnameless.factorextractor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * 
 * {@link FactorExtractor} extracts factors from {@link Factor} annotated
 * methods of input objects into a {@literal <String, Object>} Map.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class FactorExtractor {

  private FactorExtractor() {}

  /**
   * Returns a Map{@literal <String, Object>} which is extracted from annotated
   * methods of input objects.
   * 
   * @param objects
   *          an array of objects
   * @return a Map{@literal <String, Object>}
   */
  public static Map<String, Object> extract(Object... objects) {
    for (Object o : objects) {
      if (o == null)
        throw new NullPointerException("Null object is not allowed");
    }

    Map<String, Object> factors = new HashMap<String, Object>();
    for (Object o : objects) {
      Class<?> testClass = o.getClass();
      for (Method m : testClass.getMethods()) {
        Factor factor = AnnotationUtils.findAnnotation(m, Factor.class);
        if (factor != null) {
          try {
            factors.put(factor.value(), m.invoke(o));
          } catch (InvocationTargetException wrappedExc) {
            Throwable exc = wrappedExc.getCause();
            Logger.getLogger(FactorExtractor.class.getName()).log(Level.SEVERE,
                m + " failed: " + exc, wrappedExc);
          } catch (Exception exc) {
            Logger.getLogger(FactorExtractor.class.getName()).log(Level.SEVERE,
                "INVALID @Factor: " + m + exc, exc);
          }
        }
      }
    }

    return factors;
  }

}
