import 'package:burgers/burger.dart';
import 'package:burgers/order_backend.dart';
import 'package:burgers/order_burger_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:provider/provider.dart';

void main() {
  const methodChannel = MethodChannel('comfortfoodChannel');
  final backend = OrderBackend(methodChannel);
  final burger = Burger(
    name: 'The ultimate burger',
    description:
        'Our signature burger with 100% Angus beef, cheddar cheese and our secret sauce.',
    price: 8.00,
  );

  runApp(
    MultiProvider(
      providers: [
        Provider<OrderBackend>.value(value: backend),
        Provider<Burger>.value(value: burger)
      ],
      child: BurgersApp(),
    ),
  );
}

class BurgersApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mikes burgers',
      theme: ThemeData.dark().copyWith(
        textTheme: TextTheme(
          headline1: TextStyle(
            color: Color.fromARGB(255, 242, 241, 237),
            fontSize: 30.0,
            fontWeight: FontWeight.bold,
          ),
          headline2: TextStyle(
            color: Color.fromARGB(255, 242, 241, 237),
            fontSize: 20.0,
            fontWeight: FontWeight.bold,
          ),
          bodyText1: TextStyle(
              color: Color.fromARGB(255, 242, 241, 237), fontSize: 18.0),
        ),
      ),
      home: OrderBurgerScreen(),
    );
  }
}
