import React, { useState } from 'react';
import './BookingForm.css';
import 'react-datepicker/dist/react-datepicker.css';
import { useLocation, useHistory } from 'react-router-dom';
import { format, addDays } from 'date-fns';

const BookingForm = () => {
  const history = useHistory();
  const [showPaymentForm, setShowPaymentForm] = useState(false);
  
  const location = useLocation();
  const { roomData } = location.state;
  const guestId = localStorage.getItem('userId');

  const [BookingFormData, setBookingFormData] = useState({
    hotelId: roomData.hotel.hotelId,
    roomId: roomData.id,
    guestId: guestId,
    roomPrice: roomData.roomPrice,
    bookingDate: '',
    checkOutDate: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBookingFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
    setShowPaymentForm(true);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Send BookingFormData to the backend or perform desired actions
    console.log('This is the booking page:');
    console.log(BookingFormData);
    history.push(`/payment`, { BookingFormData: BookingFormData });
  };

  const isFutureDate = (date) => {
    return date > new Date();
  };

  const isCheckOutDateValid = (date) => {
    return date > BookingFormData.bookingDate;
  };

  return (
    <div className='form-container'>
      <h2>Booking Form</h2>
      <label className="search-filter-label">Hotel Name: {roomData.hotel.hotelName}</label>
      <label className="search-filter-label">Room Type: {roomData.roomType}</label>
      <label className="search-filter-label">Price: {roomData.roomPrice}</label>
    
      <div className="mb-3">
        <label className="search-filter-label">Booking Date:</label>
        <input
          type="date"
          name="bookingDate"
          min={format(addDays(new Date(), 1), 'yyyy-MM-dd')}
          value={BookingFormData.bookingDate}
          onChange={handleChange}
          className="form-control"
        />
      </div>
    
      <div className="mb-3">
        <label className="search-filter-label">Check Out Date:</label>
        <input
          type="date"
          name="checkOutDate"
          min={BookingFormData.bookingDate}
          value={BookingFormData.checkOutDate}
          onChange={handleChange}
          className="form-control"
        />
      </div>
      <label className='search-filter-label' >
        Booking Amount:
        <input className='input-amount'  type="number" placeholder={roomData.roomPrice} readOnly />
      </label>
      <button onClick={handleSubmit}>Book Now</button>
    </div>
  );
};

export default BookingForm;
