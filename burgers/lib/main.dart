import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData.dark().copyWith(
        textTheme: TextTheme(
          headline4: TextStyle(color: Colors.white),
          headline5: TextStyle(color: Colors.white),
          headline6: TextStyle(color: Colors.white),
          bodyText1: TextStyle(color: Colors.white, fontSize: 16.0),
        ),
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key}) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final _burgerPrice = 8.00;
  double _price;
  bool _hasFries;

  @override
  void initState() {
    super.initState();
    _price = 8.00;
    _hasFries = false;
  }

  void recalculatePrice({bool friesSelected}) {
    setState(() {
      _hasFries = friesSelected;
      _price = _hasFries ? _burgerPrice + 2.00 : _burgerPrice;
    });
  }

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    return Scaffold(
      backgroundColor: Colors.black54,
      body: SafeArea(
        child: Column(
          children: <Widget>[
            Image.asset('assets/images/burger.webp'),
            Padding(
              padding: const EdgeInsets.only(top: 8.0, bottom: 16.0),
              child: Text(
                'The ultimate burger',
                style: textTheme.headline4,
              ),
            ),
            Text(
              "Our signature burger with 100% Angus beef, cheddar cheese and our secret sauce.",
              style: textTheme.bodyText1,
            ),
            Padding(
              padding: const EdgeInsets.only(top: 28.0),
              child: Divider(
                color: Colors.white,
                thickness: 2,
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(top: 28.0),
              child: Row(
                children: <Widget>[
                  Text(
                    "Add fries to order:",
                    style: textTheme.headline6,
                  ),
                  Checkbox(
                    onChanged: (value) =>
                        recalculatePrice(friesSelected: value),
                    value: _hasFries,
                  )
                ],
              ),
            ),
            Expanded(
              child: Align(
                alignment: Alignment.bottomCenter,
                child: Padding(
                  padding: const EdgeInsets.only(bottom: 28.0),
                  child: Text(
                    'Total price: \$${_price}0',
                    style: textTheme.headline5,
                  ),
                ),
              ),
            )
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () =>
            SystemChannels.platform.invokeMethod<void>('SystemNavigator.pop'),
        tooltip: 'Order',
        child: Icon(Icons.check),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
