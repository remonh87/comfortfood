import 'package:burgers/order_backend.dart';
import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';

void main() {
  group('$OrderBackend', () {
    _MethodChannelMock methodChannelMock;
    OrderBackend sut;

    setUp(() {
      methodChannelMock = _MethodChannelMock();
      sut = OrderBackend(methodChannelMock);
      when(methodChannelMock.invokeMethod(any, any))
          .thenAnswer((_) => Future.value());
    });

    test('It calls methodChannel with correct arguments', () async {
      const description = 'TestOrder';
      const price = 100.0;
      await sut.completeOrder(description, price);

      verify(
        methodChannelMock.invokeMethod(
          completeOrderMethod,
          [description, price],
        ),
      ).called(1);
    });
  });
}

class _MethodChannelMock extends Mock implements MethodChannel {}
