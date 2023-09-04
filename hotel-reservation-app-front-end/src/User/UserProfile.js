import React, { useState, useEffect } from 'react';
import './UserProfile.css';
import axios from 'axios';
function UserProfile() {
  const [userData, setUserData] = useState(null);
  const headers = {
    Authorization: localStorage.getItem('token').trim()
  };
  useEffect(() => {
    // Replace 'your-api-endpoint' with your actual API endpoint
    axios
    .get(`http://localhost:8080/guest/get-user-by-id/${parseInt(localStorage.getItem('userId'))}`)
    .then((response) => {
      setUserData(response.data)})
      .catch(error => console.error(error));
  }, []);

  return (
    <div className="user-profile-container">
    <h2 className="user-profile-title">User Profile</h2>
    {userData ? (
      <div className="user-profile-data">
        <div className="user-profile-field">
          <span className="user-profile-label">Name:</span>
          <span className="user-profile-value">{userData.firstName} {userData.lastName}</span>
        </div>
        <div className="user-profile-field">
          <span className="user-profile-label">Gender:</span>
          <span className="user-profile-value">{userData.gender}</span>
        </div>
        <div className="user-profile-field">
          <span className="user-profile-label">Age:</span>
          <span className="user-profile-value">{userData.age}</span>
        </div>
        <div className="user-profile-field">
          <span className="user-profile-label">Address:</span>
          <span className="user-profile-value">{userData.address.streetAddress}, {userData.address.city}, {userData.address.state}, {userData.address.zipCode}</span>
        </div>
        <div className="user-profile-field">
          <span className="user-profile-label">Contact No:</span>
          <span className="user-profile-value">{userData.contactNo}</span>
        </div>
        <div className="user-profile-field">
          <span className="user-profile-label">Email:</span>
          <span className="user-profile-value">{userData.email}</span>
        </div>
      </div>
    ) : (
      <p>Loading user profile...</p>
    )}
  </div>
  );
}

export default UserProfile;
