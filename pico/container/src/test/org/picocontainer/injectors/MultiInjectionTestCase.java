/*****************************************************************************
 * Copyright (C) PicoContainer Organization. All rights reserved.            *
 * ------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the BSD      *
 * style license a copy of which has been included with this distribution in *
 * the LICENSE.txt file.                                                     *
 *                                                                           *
 *****************************************************************************/
package org.picocontainer.injectors;

import org.picocontainer.injectors.ConstructorInjection;
import org.picocontainer.monitors.NullComponentMonitor;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.ComponentFactory;
import org.picocontainer.Characteristics;
import org.picocontainer.injectors.ConstructorInjector;
import org.picocontainer.tck.AbstractComponentFactoryTestCase;
import org.picocontainer.tck.AbstractComponentAdapterTestCase.RecordingLifecycleStrategy;
import org.picocontainer.testmodel.NullLifecycle;
import org.picocontainer.testmodel.RecordingLifecycle;
import org.picocontainer.testmodel.RecordingLifecycle.One;

import junit.framework.TestCase;

/**
 * @author Paul Hammant
 */
public class MultiInjectionTestCase extends TestCase {

    public static class Bar {
    }
    public static class Baz {
    }
    public static class Foo {
        private final Bar bar;
        private Baz baz;

        public Foo(Bar bar) {
            this.bar = bar;
        }

        public void setBaz(Baz baz) {
            this.baz = baz;
        }
    }

    public void testBasics() throws NoSuchMethodException {
        DefaultPicoContainer dpc = new DefaultPicoContainer(new MultiInjection());
        dpc.addComponent(Bar.class);
        dpc.addComponent(Baz.class);
        dpc.addComponent(Foo.class);
        Foo foo = dpc.getComponent(Foo.class);
        assertNotNull(foo);
        assertNotNull(foo.bar);
        assertNotNull(foo.baz);
    }

}