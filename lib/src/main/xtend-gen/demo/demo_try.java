package demo;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import org.eclipse.xtext.xbase.lib.Exceptions;

@SuppressWarnings("all")
public class demo_try {
  public static void main(final String[] args) {
    try {
      final String path = "forsyde-io\\sobel-application.fiodl";
      ForSyDeModelHandler handler = null;
      ForSyDeSystemGraph model = null;
      ForSyDeModelHandler _forSyDeModelHandler = new ForSyDeModelHandler();
      handler = _forSyDeModelHandler;
      model = handler.loadModel(path);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
