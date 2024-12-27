import React from 'react';
import { useCart } from '../context/CartContext';

const Cart = () => {
  const { cart, dispatch } = useCart();

  return (
    <div>
      <h1>Your Cart</h1>
      {cart.length === 0 ? (
        <p>Your cart is empty</p>
      ) : (
        <ul>
          {cart.map((item) => (
            <li key={item.id}>
              {item.name} - ${item.price}
              <button onClick={() => dispatch({ type: 'REMOVE_FROM_CART', payload: item })}>
                Remove
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Cart;
