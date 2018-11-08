package com.aswans.designmode.observer;

import java.util.Observable;
import java.util.Observer;
/**
 * java观察者模式
 * @author 张三杰 add 2015-6-29
 *
 */
public class ObserverMode {
  public static void main(String[] args) {
      Observer consumer = new Consumer();
      MilkProvider provider = new MilkProvider();
      provider.addObserver(consumer);
      provider.milkProduced();
  }
  //主题
  static class MilkProvider extends Observable {
      public void milkProduced() {
          setChanged();//状态改变，必须调用
          notifyObservers();
      }
      public float getPrice() {
          return 2.5f;
      }
  }
  //观察者
  static class Consumer implements Observer {
      @Override
      public void update(Observable arg0, Object arg1) {
          System.out.println("Consumer update..." + arg0 + ";arg1=" + arg1);
          System.out.println("milk price =" + ((MilkProvider) arg0).getPrice());
      }
  }
}