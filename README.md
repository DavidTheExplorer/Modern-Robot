# Modern Robot
Java has an old class that controlls both the mouse and keyboard, and it suffers from:
* Boilerplate - You have to manage delays, remember to release pressed keys and in reverse order, handle CAPS LOCK...
* Horrible Readability - Tapping even 1 key requires multiple lines full of bad constant names.

This library solves those problems behind a `Mouse` and `Keyboard` abstractions that internally utilize the robot.

## Comparison to Java's Robot
Example scenario: We want to type "Hey", press enter, and click the mouse at (100, 100).


### Modern Robot
```java
//can and should be reused
Keyboard keyboard = HardwareFactory.createKeyboard();
Mouse mouse = HardwareFactory.createMouse();

keyboard.tap("Hey");
keyboard.tap(ENTER); //static import of FunctionKey

mouse.performAt(LEFT_CLICK, new Point2D(100, 100)); //static import of MouseAction
```

### Java's Robot
<details>
  <summary>I'm warning you...</summary>

  ```java
  Robot robot = new Robot(); //handle AWTException

  //caps lock
  robot.keyPress(KeyEvent.VK_CAPS_LOCK);
  robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
  smallDelay();

  //type 'H'
  robot.keyPress(KeyEvent.VK_H);
  robot.keyRelease(KeyEvent.VK_H);
  smallDelay();

  //caps lock
  robot.keyPress(KeyEvent.VK_CAPS_LOCK);
  robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
  smallDelay();

  //type 'e'
  robot.keyPress(KeyEvent.VK_E);
  robot.keyRelease(KeyEvent.VK_E);
  smallDelay();

  //type 'y'
  robot.keyPress(KeyEvent.VK_Y);
  robot.keyRelease(KeyEvent.VK_Y);
  smallDelay();

  //press enter
  robot.keyPress(KeyEvent.VK_ENTER);
  robot.keyRelease(KeyEvent.VK_ENTER);
  smallDelay();

  //move and click the mouse
  robot.mouseMove(100, 100);
  robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
  robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
  ```
</details>
