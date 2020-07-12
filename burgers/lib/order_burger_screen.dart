import 'package:burgers/order_backend.dart';
import 'package:flutter/services.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'burger.dart';

class OrderBurgerScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Selector2<Burger, OrderBackend, OrderBurgerViewModel>(
      selector: (context, burger, backend) => OrderBurgerViewModel(
        burger,
        () => SystemChannels.platform.invokeMethod<void>('SystemNavigator.pop'),
        backend.completeOrder,
      ),
      builder: (context, viewModel, _) => _OrderBurger(
        viewModel: viewModel,
      ),
    );
  }
}

@visibleForTesting
class OrderBurgerViewModel {
  OrderBurgerViewModel(
    this.burger,
    this.closeScreen,
    Future<void> Function(String name, double totalPrice) orderBurger,
  ) : _orderBurger = orderBurger;

  final Burger burger;
  final Future<void> Function(String name, double totalPrice) _orderBurger;
  final Future<void> Function() closeScreen;

  Future<void> completeOrder({bool hasFries = false}) async {
    final description = hasFries ? '${burger.name} with fries' : burger.name;
    await _orderBurger(description, orderTotalPrice(hasFries: hasFries));
    await closeScreen();
  }

  double orderTotalPrice({bool hasFries = false}) =>
      hasFries ? burger.price + 2.0 : burger.price;

  @override
  bool operator ==(Object other) {
    return other is OrderBurgerViewModel && burger == other.burger;
  }

  @override
  int get hashCode => 37 * hashCode + burger.hashCode;
}

class _OrderBurger extends StatefulWidget {
  const _OrderBurger({
    @required this.viewModel,
    Key key,
  })  : assert(viewModel != null),
        super(key: key);

  final OrderBurgerViewModel viewModel;

  @override
  _OrderBurgerState createState() => _OrderBurgerState();
}

class _OrderBurgerState extends State<_OrderBurger> {
  bool _hasFries;

  @override
  void initState() {
    super.initState();
    _hasFries = false;
  }

  void recalculatePrice({bool friesSelected}) {
    setState(() {
      _hasFries = friesSelected;
    });
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
                widget.viewModel.burger.name,
                style: textTheme.headline1,
              ),
              Padding(
                padding: const EdgeInsets.only(top: 16.0),
                child: Text(
                  widget.viewModel.burger.description,
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
                      '${widget.viewModel.orderTotalPrice(hasFries: _hasFries)}0',
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
          onPressed: () => widget.viewModel.completeOrder(hasFries: _hasFries),
          backgroundColor: Color.fromARGB(255, 242, 241, 237),
          tooltip: 'Order',
          child: Icon(Icons.check),
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
