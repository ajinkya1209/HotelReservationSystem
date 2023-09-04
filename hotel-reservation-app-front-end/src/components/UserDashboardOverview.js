import React, { useState, useEffect } from 'react';

function UserDashboardOverview() {
  const [userData, setUserData] = useState(null); // Replace with user data fetched from backend
  const [bookings, setBookings] = useState([]); // Replace with user bookings fetched from backend

  useEffect(() => {
    // Replace with API call to fetch user data from backend
    fetchUserDataFromBackend().then(data => setUserData(data));

    // Replace with API call to fetch user bookings from backend
    fetchUserBookingsFromBackend().then(data => setBookings(data));
  }, []);

  const handleBookingSubmit = (formData) => {
    // Replace with API call to submit booking to the backend
    // Example: submitBooking(formData).then(response => console.log(response));
    console.log("Booking submitted:", formData);
  };

  return (
    <div>
      <h2>User Dashboard Overview</h2>
      {userData && (
        <div>
          <h3>Welcome, {userData.name}!</h3>
          <p>Email: {userData.email}</p>
          {/* Display other user details */}
        </div>
      )}

      <h4>Your Bookings:</h4>
      <ul>
        {bookings.map(booking => (
          <li key={booking.id}>
            <strong>Booking ID:</strong> {booking.id}<br />
            <strong>Hotel:</strong> {booking.hotelName}<br />
            <strong>Room Type:</strong> {booking.roomType}<br />
            <strong>Check-in:</strong> {booking.checkInDate}<br />
            <strong>Check-out:</strong> {booking.checkOutDate}
          </li>
        ))}
      </ul>

      <h4>Book a Room:</h4>
      <BookingForm onSubmit={handleBookingSubmit} />

      {/* Other important information */}
    </div>
  );
}

function BookingForm({ onSubmit }) {
  const [formData, setFormData] = useState({
    hotel: '',
    roomType: '',
    checkInDate: '',
    checkOutDate: '',
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData(prevData => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit(formData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Hotel:
        <input type="text" name="hotel" value={formData.hotel} onChange={handleChange} />
      </label>
      <label>
        Room Type:
        <input type="text" name="roomType" value={formData.roomType} onChange={handleChange} />
      </label>
      <label>
        Check-in Date:
        <input type="date" name="checkInDate" value={formData.checkInDate} onChange={handleChange} />
      </label>
      <label>
        Check-out Date:
        <input type="date" name="checkOutDate" value={formData.checkOutDate} onChange={handleChange} />
      </label>
      <button type="submit">Book Now</button>
    </form>
  );
}

// Replace with your API call to fetch user data
function fetchUserDataFromBackend() {
  // Example: Fetch user data from backend API
  return {
    name: 'John Doe',
    email: 'john@example.com',
    // Add more user data fields as needed
  };
}

// Replace with your API call to fetch user bookings
function fetchUserBookingsFromBackend() {
  // Example: Fetch user bookings from backend API
  return [
    {
      id: 1,
      hotelName: 'Luxury Hotel',
      roomType: 'Deluxe Room',
      checkInDate: '2023-09-01',
      checkOutDate: '2023-09-05',
    },
    // Add more bookings as needed
  ];
}

export default UserDashboardOverview;
