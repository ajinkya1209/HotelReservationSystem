import React, { useState } from 'react';
import HotelType from './HotelType';
import './AddHotel.css'

const HotelForm = () => {
  const [formData, setFormData] = useState({
    hotelName: '',
    aboutHotel: '',
    address: {
      streetAddress: "",
      city: "",
      state: "",
      zipCode: ""
    },
    contactNo: '',
    hotelType: '',
    hotelAmenities: '',
    roomCapacity: '',
  });

  const [errors, setErrors] = useState({
    hotelName: '',
    aboutHotel: '',
    address: '',
    contactNo: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const validateForm = () => {
    const newErrors = {};
    if (formData.hotelName.length < 3) {
      newErrors.hotelName = 'Hotel name must be at least 3 characters long';
    }
    if (formData.aboutHotel.length < 25) {
      newErrors.aboutHotel = 'About hotel must be at least 25 characters long';
    }
    if (formData.address.length < 3) {
        newErrors.address = 'Hotel name must be at least 3 characters long';
      }
    if (!/^\d{1,10}$/.test(formData.contactNo)) {
      newErrors.contactNo = 'Contact number must contain 1 to 10 digits';
    }


    setErrors(newErrors);
    return Object.keys(newErrors).length === 0; // If no errors, form is valid
  };

  return (
        <div className="hotel-form">
          <h2>Add a New Hotel</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label className='input-label'>Hotel Name</label>
              <input
                type="text"
                name="hotelName"
                value={formData.hotelName}
                onChange={handleChange}
                placeholder='Enter Hotel Name'
                className={`form-control ${errors.hotelName ? 'is-invalid' : ''}` }

              />
              {errors.hotelName && <div className="invalid-feedback">{errors.hotelName}</div>}
            </div>
      
            <div className="form-group">
              <label className='input-label'>About Hotel</label>
              <textarea
                name="aboutHotel"
                value={formData.aboutHotel}
                onChange={handleChange}
                placeholder='Enter About Hotel'
                className={`form-control ${errors.aboutHotel ? 'is-invalid' : ''}`}
              />
              {errors.aboutHotel && <div className="invalid-feedback">{errors.aboutHotel}</div>}
            </div>
      
            <div className="form-group">
              <label className='input-label'>Address</label>
              <input
                type="text"
                name="address"
                value={formData.address}
                onChange={handleChange}
                className={`form-control ${errors.address ? 'is-invalid' : ''}` }

                />
                {errors.address && <div className="invalid-feedback">{errors.address}</div>}
            
            </div>
      
            <div className="form-group">
              <label className='input-label'>Contact No</label>
              <input
                type="text"
                name="contactNo"
                value={formData.contactNo}
                onChange={handleChange}
                placeholder='example-7854223322'
                className={`form-control ${errors.contactNo ? 'is-invalid' : ''}`}
              />
              {errors.contactNo && <div className="invalid-feedback">{errors.contactNo}</div>}
            </div>
      
            <div className="form-group">
            <label className="input-label" htmlFor="hotelType">Hotel Type</label>
            <HotelType name="hotelType" />
          </div>
      
            <div className="form-group">
              <label className='input-label'>Hotel Amenities</label>
              <input
                type="text"
                name="hotelAmenities"
                value={formData.hotelAmenities}
                onChange={handleChange}
                placeholder='Enter hotel Amenities'
                className="input-field"
              />
            </div>
      
            <div className="form-group">
              <label className='input-label'>Room Capacity</label>
              <input
                type="number"
                name="roomCapacity"
                value={formData.roomCapacity}
                onChange={handleChange}
                placeholder='Enter Room Capacity'
                className="input-field"
              />
            </div>
      
            <button type="submit" className="btn btn-primary">Submit</button>
          </form>
        </div>
      );
};

export default HotelForm;
