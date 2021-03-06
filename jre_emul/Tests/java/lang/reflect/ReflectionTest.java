/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang.reflect;

import junit.framework.TestCase;

/**
 * Miscellaneous tests for J2ObjC's reflection support.
 */
public class ReflectionTest extends TestCase {

  static class NoEquals {};

  // Assert equals method can be found using reflection. Because it's a mapped
  // method with a parameter, reflection was trying to find "equalsWithId:"
  // instead of "isEqual:".
  public void testEqualsMethodLookup() throws Exception {
    Method m = Integer.class.getMethod("equals", new Class<?>[] { Object.class });
    assertNotNull(m);
    Integer uno = new Integer(1);
    Integer dos = new Integer(2);
    Boolean b = (Boolean) m.invoke(uno,  new Object[] { dos });
    assertFalse(b);
    b = (Boolean) m.invoke(uno,  new Object[] { uno });
    assertTrue(b);

    NoEquals obj1 = new NoEquals();
    NoEquals obj2 = new NoEquals();
    m = NoEquals.class.getMethod("equals", new Class<?>[] { Object.class });
    assertNotNull(m);
    assertFalse ((Boolean) m.invoke(obj1, obj2));
    assertTrue ((Boolean) m.invoke(obj1, obj1));
  }

}
