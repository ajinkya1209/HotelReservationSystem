import React, { useState } from 'react';
import axios from 'axios';
import { useLocation, useParams } from 'react-router-dom/cjs/react-router-dom';

import { Card} from 'react-bootstrap';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import { Button } from 'react-bootstrap';

function EditHotel(props) {
    const history=useHistory();
    ///console.log(props);
    const location = useLocation(); // Initialize useLocation hook
  const { hotelData } = location.state; 
  const [formData, setFormData] = useState({
    hotelId: hotelData.hotelId,
    aboutHotel: hotelData.aboutHotel,
    address: {
      streetAddress: hotelData.address.streetAddress,
      city: hotelData.address.city,
      state: hotelData.address.state,
      zipCode: hotelData.address.zipCode,
    },
    contactNo: hotelData.contactNo,
    hotelAmenities: hotelData.hotelAmenities,
    hotelName: hotelData.hotelName,
    hotelType: hotelData.hotelType,
    roomCapacity: hotelData.roomCapacity,
  });
  // Handle changes in form inputs
  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.startsWith('address.')) {
      const fieldName = name.split('.')[1];
      setFormData({
        ...formData,
        address: {
          ...formData.address,
          [fieldName]: value,
        },
      });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const headers = {
    Authorization: localStorage.getItem('token'),
  };

  
  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(e.target);

    // Validate contactNo (must be 10 digits)
    if (formData.contactNo.length !== 10) {
      alert('Contact number must be 10 digits.');
      return;
    }

    // Validate zipCode (must be 6 digits)
    if (!/^\d{6}$/.test(formData.address.zipCode)) {
      alert('Zip code must be 6 digits.');
      return;
    }

    ///{hotelId}/hotelName/{hotelName}
    try {
      
      await axios.put(`http://localhost:8080/hotel/${formData.hotelId}`, formData, { headers });
      alert('Hotel updated successfully!');
     
    } catch (error) {
      console.error('Error updating hotel:', error);
     
      alert('Error updating hotel. Please try again.');
    }
  };

  return (
    <div >
      <h1>Edit Hotel</h1>
      <Card.Text style={{ fontWeight: 'bold', color: 'red' }}>(Fields marked with * are mandatory)</Card.Text>
      <form onSubmit={handleSubmit}>
        
        <div className="mb-3">
          <label htmlFor="hotelId" className="form-label">
            Hotel Id
          </label>
          <input
            type="text"
            className="form-control"
            id="hotelId"
            name="hotelId"
            value={formData.hotelId}
            onChange={handleChange}
            required
            readOnly
          />
        </div>

       
        <div className="mb-3">
          <label htmlFor="hotelName" className="form-label">
            Hotel Name <label style={{color:"red"}}> *</label>
          </label>
          <input
            type="text"
            className="form-control"
            id="hotelName"
            name="hotelName"
            value={formData.hotelName}
            onChange={handleChange}
            required
          />
        </div>

        
        <div className="mb-3">
          <label htmlFor="aboutHotel" className="form-label">
            Hotel Description
          </label>
          <textarea
            className="form-control"
            id="aboutHotel"
            name="aboutHotel"
            value={formData.aboutHotel}
            onChange={handleChange}
            rows="3"
          ></textarea>
        </div>

        
        <div className="mb-3">
          <label htmlFor="address.city" className="form-label">
            City<label style={{color:"red"}}> *</label>
          </label>
          <input
            type="text"
            className="form-control"
            id="address.city"
            name="address.city"
            value={formData.address.city}
            onChange={handleChange}
            required
          />
        </div>

        
        <div className="mb-3">
          <label htmlFor="address.state" className="form-label">
            State<label style={{color:"red"}}> *</label>
          </label>
          <input
            type="text"
            className="form-control"
            id="address.state"
            name="address.state"
            value={formData.address.state}
            onChange={handleChange}
            required
          />
        </div>

        
        <div className="mb-3">
          <label htmlFor="address.streetAddress" className="form-label">
            Street Address<label style={{color:"red"}}> *</label>
          </label>
          <input
            type="text"
            className="form-control"
            id="address.streetAddress"
            name="address.streetAddress"
            value={formData.address.streetAddress}
            onChange={handleChange}
            required
          />
        </div>

        
        <div className="mb-3">
          <label htmlFor="address.zipCode" className="form-label">
            Zip Code <label style={{color:"red"}}> * (Must be 6-digit)</label>
          </label>
          <input
            type="text"
            className="form-control"
            id="address.zipCode"
            name="address.zipCode"
            pattern="[0-9]*"
            value={formData.address.zipCode}
            onChange={handleChange}
            required
          />
        </div>

        
        <div className="mb-3">
          <label htmlFor="contactNo" className="form-label">
            Hotel Contact <label style={{color:"red"}}> * (Must be 10 digit)</label>
          </label>
          <input
            type="tel"
            className="form-control"
            id="contactNo"
            name="contactNo"
            pattern="[0-9]*"
            value={formData.contactNo}
            onChange={handleChange}
            required
          />
        </div>

        
        <div className="mb-3">
          <label htmlFor="hotelAmenities" className="form-label">
            Hotel Amenities
          </label>
          <input
            type="text"
            className="form-control"
            id="hotelAmenities"
            name="hotelAmenities"
            value={formData.hotelAmenities}
            onChange={handleChange}
          />
        </div>

        
        <div className="mb-3">
          <label htmlFor="hotelType" className="form-label">
            Hotel Type<label style={{color:"red"}}> *</label>
          </label>
          <select
            className="form-select"
            id="hotelType"
            name="hotelType"
            value={formData.hotelType}
            onChange={handleChange}
            required
          >
            <option value="FIVE_STAR">Five Star</option>
            <option value="FOUR_STAR">Four Star</option>
            <option value="THREE_STAR">Three Star</option>
            <option value="TWO_STAR">Two Star</option>
          </select>
        </div>

        
        <div className="mb-3">
          <label htmlFor="roomCapacity" className="form-label">
            Room Capacity
          </label>
          <input
            type="text"
            className="form-control"
            id="roomCapacity"
            name="roomCapacity"
            value={formData.roomCapacity}
            onChange={handleChange}
          />
        </div>

        <div className="mb-3">
          <button type="submit" className="btn btn-primary">
            Update Hotel
          </button>
            <Button variant="primary"   onClick={()=>history.push('/hotel-list-admin')} >
              Back
           </Button>
        </div>
      </form>
      </div>
  );
}

export default EditHotel;
