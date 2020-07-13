import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';


class OrderBackend {
  OrderBackend(this.orderMethodChannel);

  final MethodChannel orderMethodChannel;

  Future<void> completeOrder(String orderDescription, double totalPrice) async {
    await orderMethodChannel.invokeMethod(completeOrderMethod, [
      orderDescription,
      totalPrice,
    ]);
  }
}

@visibleForTesting
const completeOrderMethod = 'completeOrder';