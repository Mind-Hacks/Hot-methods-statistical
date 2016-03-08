/*
* Copyright (c) 2015 Sohu TV. All rights reserved.
*/
package com.sohu.dao;

import org.springframework.stereotype.Repository;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2015年12月25日下午6:05:58
 */
@Repository
public class DaoMethod {
       public void method1(){
           try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }

       public void method2(){
           try {
               Thread.currentThread().sleep(10);
           } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }

       public void method3(){
           try {
            Thread.currentThread().sleep(133);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }

       public void method4(){
           try {
            Thread.currentThread().sleep(77);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       }

       public void method5(){
           try {
               Thread.currentThread().sleep(30);
           } catch (InterruptedException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }
}
