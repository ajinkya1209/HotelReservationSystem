import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const AdminDashboard = () => {
  const [hotelList, setHotelList] = useState([]);

  useEffect(() => {
    
    fetch('/api/hotels')
      .then(response => response.json())
      .then(data => setHotelList(data))
      .catch(error => console.error('Error fetching hotels:', error));
  }, []);

  return (
    <div className="admin-dashboard">
      <h2>Admin Dashboard</h2>
      <nav>
        <ul>
          <li>
            <Link to="/admin/add-hotel">Add Hotel</Link>
          </li>
          <li>
            <Link to="/admin/delete-hotel">Delete Hotel</Link>
          </li>
          <li>
            <Link to="/admin/update-hotel">Update Hotel</Link>
          </li>
        </ul>
      </nav>
      <div className="hotel-list">
        <h3>Hotel List</h3>
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>About</th>
              <th>Address</th>
              <th>Contact No</th>
              <th>Hotel Type</th>
              <th>Amenities</th>
              <th>Room Capacity</th>
            </tr>
          </thead>
          <tbody>
            {hotelList.map(hotel => (
              <tr key={hotel.id}>
                <td>{hotel.name}</td>
                <td>{hotel.about}</td>
                <td>{hotel.address}</td>
                <td>{hotel.contactNo}</td>
                <td>{hotel.hotelType}</td>
                <td>{hotel.amenities}</td>
                <td>{hotel.roomCapacity}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AdminDashboard;
