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

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class FactorExtractorTest {

  @Test
  public void testFactorExtractor() {
    Map<String, Object> factors = FactorExtractor.extract(new FactorBean());

    assertEquals("id", factors.get("id"));
    assertEquals(123, factors.get("num"));
  }

  @Test
  public void testFactorExtractorWithExtendedClass() {
    Map<String, Object> factors =
        FactorExtractor.extract(new ExtendedFactorBean());

    assertEquals("id", factors.get("id"));
    assertEquals(123, factors.get("num"));
  }

  @Test(expected = NullPointerException.class)
  public void testFactorExtractorWithNullObject() {
    FactorExtractor.extract(new FactorBean(), null);
  }

}
