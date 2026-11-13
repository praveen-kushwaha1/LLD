# Factory Design Pattern in Java — Revision Notes

## 1. What is the Factory Design Pattern?

The **Factory Design Pattern** is a creational design pattern that centralizes object creation and hides the object-instantiation logic from the client.

In simple words:

> Instead of creating objects directly with `new`, the client asks a factory to create the required object.

### Real-world analogy

Imagine a vehicle manufacturing factory. A customer requests a Car, Bike, or Truck. The factory decides which concrete vehicle object to create and returns it.

The customer does not need to know the internal creation details.

## 2. Main participants

| Participant | Responsibility | Example |
|---|---|---|
| Product | Common contract for created objects | `Vehicle` |
| Concrete Product | Actual implementation | `Car`, `Bike`, `Truck` |
| Factory | Creates the requested concrete product | `VehicleFactory` |
| Client | Requests and uses the product | `Main` |

## 3. Project structure

```text
factory-design-pattern/
└── src/
    └── com/
        └── designpattern/
            └── factory/
                ├── Vehicle.java
                ├── Car.java
                ├── Bike.java
                ├── Truck.java
                ├── VehicleFactory.java
                └── Main.java
```

## 4. Implementation step by step

### Step 1: Create the Vehicle interface

**Vehicle.java**

```java
package com.designpattern.factory;

// Common contract shared by all vehicle types.
public interface Vehicle {

    void drive();
}
```

**Remember:** The interface lets the client work with a common abstraction instead of depending on each concrete class.

### Step 2: Create concrete products

**Car.java**

```java
package com.designpattern.factory;

public class Car implements Vehicle {

    @Override
    public void drive() {
        System.out.println("Driving a Car");
    }
}
```

**Bike.java**

```java
package com.designpattern.factory;

public class Bike implements Vehicle {

    @Override
    public void drive() {
        System.out.println("Driving a Bike");
    }
}
```

**Truck.java**

```java
package com.designpattern.factory;

public class Truck implements Vehicle {

    @Override
    public void drive() {
        System.out.println("Driving a Truck");
    }
}
```

**Remember:** Each concrete product implements the same `Vehicle` interface but provides its own behavior.

### Step 3: Create the factory

**VehicleFactory.java**

```java
package com.designpattern.factory;

// Centralizes the logic for creating vehicle objects.
public class VehicleFactory {

    public static Vehicle getVehicle(String type) {

        if (type == null) {
            throw new IllegalArgumentException(
                    "Vehicle type cannot be null"
            );
        }

        switch (type.trim().toUpperCase()) {
            case "CAR":
                return new Car();

            case "BIKE":
                return new Bike();

            case "TRUCK":
                return new Truck();

            default:
                throw new IllegalArgumentException(
                        "Invalid vehicle type: " + type
                );
        }
    }
}
```

**Important points:**

- The factory receives a type, such as `"CAR"`.
- It decides which concrete class to instantiate.
- It returns the object through the `Vehicle` interface.
- It handles invalid or unsupported types.
- `static` is used here so the example can call the factory without creating a factory object. A factory does not have to be static.

### Step 4: Use the factory from the client

**Main.java**

```java
package com.designpattern.factory;

public class Main {

    public static void main(String[] args) {

        // Client asks the factory to create a Car.
        Vehicle car = VehicleFactory.getVehicle("CAR");
        car.drive();

        // Client asks the factory to create a Bike.
        Vehicle bike = VehicleFactory.getVehicle("BIKE");
        bike.drive();

        // Client asks the factory to create a Truck.
        Vehicle truck = VehicleFactory.getVehicle("TRUCK");
        truck.drive();
    }
}
```

### Expected output

```text
Driving a Car
Driving a Bike
Driving a Truck
```

## 5. How object creation works

For this statement:

```java
Vehicle car = VehicleFactory.getVehicle("CAR");
```

The flow is:

1. The client calls `getVehicle("CAR")`.
2. The factory receives the requested type.
3. The factory checks the type.
4. The factory executes `new Car()`.
5. The factory returns the new object as a `Vehicle` reference.
6. The client calls `drive()`.
7. Java runs `Car`'s overridden `drive()` method through runtime polymorphism.

```text
Main (Client)
    |
    | getVehicle("CAR")
    v
VehicleFactory
    |
    | checks "CAR"
    v
new Car()
    |
    | returns as Vehicle
    v
Main
    |
    | car.drive()
    v
"Driving a Car"
```

## 6. Why does the factory return `Vehicle` instead of `Car`?

```java
public static Vehicle getVehicle(String type)
```

`Car`, `Bike`, and `Truck` all implement `Vehicle`. Therefore, each object can be returned using the common interface type.

This allows the client to write:

```java
Vehicle vehicle = VehicleFactory.getVehicle("BIKE");
vehicle.drive();
```

The client does not need to know the concrete class to call behavior declared in the interface.

This is an example of **programming to an interface** and uses **polymorphism**.

## 7. Without Factory vs. With Factory

### Without Factory

```java
if (type.equals("CAR")) {
    Car car = new Car();
    car.drive();
} else if (type.equals("BIKE")) {
    Bike bike = new Bike();
    bike.drive();
} else if (type.equals("TRUCK")) {
    Truck truck = new Truck();
    truck.drive();
}
```

The client contains object-creation decisions and depends directly on concrete classes.

### With Factory

```java
Vehicle vehicle = VehicleFactory.getVehicle(type);
vehicle.drive();
```

The creation decision is moved into one place: `VehicleFactory`.

| Without Factory | With Factory |
|---|---|
| Client creates concrete objects | Factory creates concrete objects |
| Creation logic lives in client code | Creation logic is centralized |
| Client knows concrete classes | Client can depend on `Vehicle` |
| Creation changes may affect many clients | Creation changes are often localized |

## 8. Advantages

1. **Centralized object creation:** One place controls how vehicle objects are created.
2. **Reduced coupling:** Client code can depend on the `Vehicle` abstraction rather than concrete classes.
3. **Encapsulation:** The client does not need to know the instantiation details.
4. **Reusability:** Multiple clients can use the same factory.
5. **Maintainability:** Creation logic is easier to locate and manage.

## 9. Disadvantages and limitations

1. The factory can become large when many product types are added.
2. In this switch-based implementation, adding a new type requires modifying the factory.
3. The client still needs to provide a valid type string.
4. A simple factory is not automatically flexible enough for every object-creation problem.

### Open-Closed Principle note

This example reduces coupling, but it does **not** fully follow the Open-Closed Principle: adding a new vehicle type requires changing the `switch` statement.

For more extensible object creation, study the GoF **Factory Method** and **Abstract Factory** patterns.

## 10. Factory Pattern vs. Factory Method Pattern

These terms are often confused in interviews.

| Simple Factory (this example) | GoF Factory Method |
|---|---|
| A commonly used object-creation idiom | One of the 23 Gang of Four patterns |
| Usually one factory class selects a product | A creator hierarchy delegates creation to subclasses |
| Often uses `if` or `switch` | Often uses an overridable factory method |
| Adding a product may require editing the factory | New creator subclasses can introduce new products |

**Interview clarification:** The switch-based `VehicleFactory` shown in this guide is commonly called *Simple Factory*. It is not itself one of the 23 official GoF patterns, even though it is widely taught alongside Factory Method.

## 11. Interview questions and answers

### Q1. What is the Factory Design Pattern?

It is a creational design approach that centralizes object creation and hides concrete instantiation details from the client.

### Q2. Why is Factory a creational pattern?

Because its main responsibility is creating objects.

### Q3. What is the role of the `Vehicle` interface?

It defines a common contract for all vehicle implementations and allows the client to use polymorphism.

### Q4. Why does the factory return an interface?

Returning `Vehicle` keeps client code independent of whether the actual object is a `Car`, `Bike`, or `Truck`.

### Q5. What happens if an unsupported type is passed?

The example throws `IllegalArgumentException`. This makes invalid input explicit instead of returning `null`.

### Q6. Is Simple Factory an official GoF pattern?

No. Simple Factory is a commonly used idiom. Factory Method and Abstract Factory are among the 23 GoF design patterns.

### Q7. Does this example follow the Open-Closed Principle?

Not completely. Adding a new vehicle requires modifying the factory's switch statement.

### Q8. Can a factory method be non-static?

Yes. Static is only a choice in this example. A factory can be an instance method, especially when it needs configuration, dependencies, or subclass customization.

## 12. Quick revision sheet

| Term | Remember |
|---|---|
| Pattern category | Creational |
| Product interface | `Vehicle` |
| Concrete products | `Car`, `Bike`, `Truck` |
| Factory class | `VehicleFactory` |
| Factory method in example | `getVehicle(String type)` |
| Client | `Main` |
| Main idea | Client asks factory to create an object |
| Key concepts used | Abstraction and polymorphism |

### One-line memory trick

**Client → Factory → Concrete Object**

The client requests a vehicle, the factory creates the correct concrete object, and the client uses it through the `Vehicle` interface.

## 13. Practice exercises

Try these without looking at the solution:

1. Add a `Bus` class that implements `Vehicle`.
2. Update the factory to support `"BUS"`.
3. Pass `"plane"` and observe the exception.
4. Pass `null` and observe the exception.
5. Change the factory method to an instance method and call it through a factory object.
6. Explain why `Vehicle vehicle` is preferable to `Car vehicle` when the client only needs `drive()`.

## 14. 30-second interview answer

"The Factory Design Pattern is a creational pattern used to centralize object creation and hide instantiation details from client code. For example, in a vehicle application, the client asks a `VehicleFactory` for a Car, Bike, or Truck. The factory creates the appropriate class and returns it through the common `Vehicle` interface. This reduces direct dependency on concrete classes and makes object creation easier to maintain. The switch-based example is commonly called Simple Factory; it is different from the GoF Factory Method pattern."
