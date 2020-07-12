import 'package:flutter/foundation.dart';

class Burger {
  const Burger({
    @required this.name,
    @required this.description,
    @required this.price,
  })  : assert(name != null),
        assert(description != null),
        assert(price != null);

  final String name;
  final String description;
  final double price;

  @override
  bool operator ==(Object other) {
    return other is Burger &&
        name == other.name &&
        description == other.description &&
        price == other.price;
  }

  @override
  int get hashCode =>
      37 * name.hashCode + description.hashCode + price.hashCode;

  @override
  String toString() =>
      '$Burger(name: $name, desc: $description, price: $price)';
}
