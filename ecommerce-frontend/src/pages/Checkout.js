import React from 'react';
import { useCart } from '../context/CartContext';
import { createOrder } from '../services/orderService';

const Checkout = () => {
  const { cart, dispatch } = useCart();

  const handleCheckout = async () => {
    try {
      const order = { items: cart };
      const response = await createOrder(order);
      alert(`Order placed successfully! Order ID: ${response.id}`);
      dispatch({ type: 'CLEAR_CART' });
    } catch (error) {
      alert('Failed to place order');
    }
  };

  return (
    <div>
      <h1>Checkout</h1>
      <button onClick={handleCheckout}>Place Order</button>
    </div>
  );
};

export default Checkout;
