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
  static const platformChannel = MethodChannel('comfortfoodChannel');

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

  Future<void> completeOrder() async {
    await platformChannel.invokeMethod('completeOrder', _price);

    SystemChannels.platform.invokeMethod<void>('SystemNavigator.pop');
  }

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    return Scaffold(
      backgroundColor: Colors.black54,
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsetsDirectional.fromSTEB(16.0, 40.0, 16.0, 0.0),
          child: Column(
            children: <Widget>[
              Text(
                'The ultimate burger',
                style: textTheme.headline1,
              ),
              Padding(
                padding: const EdgeInsets.only(top: 16.0),
                child: Text(
                  "Our signature burger with 100% Angus beef, cheddar cheese and our secret sauce.",
                  style: textTheme.bodyText1,
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(top: 32.0),
                child: Row(
                  children: <Widget>[
                    Text(
                      "Add fries to order:",
                      style: textTheme.bodyText1,
                    ),
                    Checkbox(
                      onChanged: (value) =>
                          recalculatePrice(friesSelected: value),
                      value: _hasFries,
                    )
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(top: 70.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Align(
                      alignment: Alignment.topCenter,
                      child: Text(
                        '\$',
                        style: textTheme.headline2,
                      ),
                    ),
                    Text(
                      '${_price}0',
                      style: textTheme.headline1,
                    ),
                  ],
                ),
              ),
              SizedBox(
                  width: double.infinity,
                  height: 300,
                  child: Image.asset('assets/images/burger.webp')),
            ],
          ),
        ),
      ),
      floatingActionButton: Padding(
        padding: const EdgeInsets.only(bottom: 18.0),
        child: FloatingActionButton(
          onPressed: completeOrder,
          backgroundColor: Color.fromARGB(255, 242, 241, 237),
          tooltip: 'Order',
          child: Icon(Icons.check),
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
