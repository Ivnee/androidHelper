package com.geek.infoandroid.android.Level3libraries.Patterns.SOLID.d;


// Данный принцип гласит, что:
// Во-первых, классы высокого уровня не должны зависеть от низкоуровневых классов. При этом оба должны зависеть от абстракций.
// Во-вторых, абстракции не должны зависеть от деталей, но детали должны зависеть от абстракций.

// Классы высокого уровня реализуют бизнес-правила или логику в системе (приложении).
// Низкоуровневые классы занимаются более подробными операциями, другими словами, они могут заниматься записью информации
// в базу данных или передачей сообщений в операционную систему или службы и т.п.
//
// Говорят, что высокоуровневый класс, который имеет зависимость от классов низкого уровня или какого-либо другого класса
// и много знает о других классах, с которыми он взаимодействует, тесно связан.
// Когда класс явно знает о дизайне и реализации другого класса, возникает риск того, что изменения в одном классе нарушат другой класс.
//
// Поэтому мы должны держать эти высокоуровневые и низкоуровневые классы слабо связанными, насколько мы можем.
// Чтобы сделать это, нам нужно сделать их зависимыми от абстракций, а не друг от друга.

public class Email {

    public void Send() {
        // код для отправки email-письма
    }
}

// Уведомление
class Notification {
    private Email email;

    public Notification() {
        email = new Email();
    }

    public void EmailDistribution() {
        email.Send();
    }
}

// Теперь класс Notification полностью зависит от класса Email, потому что он отправляет только один тип уведомлений.
// А если мы захотим ввести какие-либо другие уведомления? Тогда нам понадобится изменить всю систему уведомлений.
// В данном случае это система является тесно связанной. Что мы можем сделать, чтобы она была слабо связанной?

interface IMessenger {
    void send();
}

class Email2 implements IMessenger {

    @Override
    public void send() {
        // код для отправки email-письма
    }
}

class SMS implements IMessenger {

    @Override
    public void send() {
        // код для отправки SMS
    }
}

// Уведомление
class Notification2 {
    private IMessenger _messenger;

    public Notification2() {
        _messenger = new Email2();
    }

    public void doNotify() {
        _messenger.send();
    }
}

// В данном случае класс Notification все еще зависит от класса Email, т.к. использует его объект в конструкторе.
// В данном случае мы можем использовать принцип внедрения зависимостей (dependency injection – DI)
// Мы делегируем создание нужной нам зависимости вышестоящему классу и зависимость будем получать например в констуркторе.

// Уведомление
class Notification3 {
    private IMessenger _messenger;

    public Notification3(IMessenger _messenger) {
        this._messenger = _messenger;
    }

    public void doNotify() {
        _messenger.send();
    }
}