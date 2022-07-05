package com.sulongx.patterns.bridgepattern;

/**
 * @author sulongx
 * @version 1.0
 * @description 桥接模式: 将抽象与现实分离,使它们可以独立变化。利用组合关系代替继承关系实现,从而降低抽象和实现这两个可变维度的耦合。
 * 优点:
 *      1.抽象与实现分离，扩展能力强。
 *      2.符合开闭原则。
 *      3.符合合成复用原则。
 *      4.其实现细节对客户透明。
 * 缺点:
 *      因聚合关系建立在抽象层，需要开发者针对抽象化进行设计，正确识别系统中两个独立变化的维度。
 *
 * 适用场景:
 *      1. 当一个类存在两个独立变化的维度，且这两个维度都需要进行扩展时。
 *      2. 当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时。
 *      3.当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。
 *
 * 模块列举:
 *      1. 日志管理系统(操作日志、交易日志、异常日志、本地记录日志、异地记录日志等)
 *      2. 人力资源系统奖金模块（个人奖金、团队奖金、激励奖金、人事部门、销售部门、研发部门等）
 *      3. 消息处理（普通消息、加急消息、特急消息、系统内消息、手机短信、邮件等）
 * @date 2022/7/1 15:51
 **/
public class BridgeTest {
    public static void main(String[] args) {
        Implementor implementor = new ConcreteImplementor();
        Abstraction action = new RefinedAbstraction(implementor);
        action.Operation();
    }
}
