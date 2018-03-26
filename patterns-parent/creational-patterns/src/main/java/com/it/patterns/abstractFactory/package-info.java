/**
 * <pre>
 *     1.抽象工厂
 *     2.具体工厂
 *     
 *     3.抽象产品
 *     4.具体产品
 *     
 *     相对与 Factory Method,Abstract Factory 横向进行了扩展，即将工厂进行了泛化，
 *     不只局限与生产一种或一类产品，更符合现实
 *     
 *     <b>模式缺点：</b>
 *           当抽象工厂生产一种新兴的产品时，如空气处理器，需要修改抽象工厂，违反了"开闭"原则
 *     
 *     
 *     <b>适用场景：</b>
 *          产品等级结构稳定，如手机当今主要分为IOS and Android OS,windows phone,
 *          市场稳定,很难或者说没必要创造一个全新的OS来迎合市场，市场已接近饱和
 * </pre>
 */
package com.it.patterns.abstractFactory;