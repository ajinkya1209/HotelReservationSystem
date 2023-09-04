import React, { useState, useEffect } from 'react';

const UpdateHotelForm = () => {
  const [hotelId, setHotelId] = useState('');
  const [hotelData, setHotelData] = useState({
    hotelName: '',
    aboutHotel: '',
    address: '',
    contactNo: '',
    hotelType: '',
    hotelAmenities: '',
    roomCapacity: '',
  });

  const [errors, setErrors] = useState({});
  const [isFetching, setIsFetching] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setHotelData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
  };

  const fetchHotelDetails = async () => {
    
    try {
      setIsFetching(true);
      const response = await fetch(`/api/hotels/${hotelId}`);
      const data = await response.json();
      setHotelData(data); 
    } catch (error) {
      console.error('Error fetching hotel details:', error);
    } finally {
      setIsFetching(false);
    }
  };

  useEffect(() => {
    if (hotelId) {
      fetchHotelDetails();
    }
  }, [hotelId]);

  return (
    <div className="update-hotel-form">
      <h2>Update Hotel Details</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Hotel ID</label>
          <input
            type="text"
            value={hotelId}
            onChange={(e) => setHotelId(e.target.value)}
          />
        </div>

      <div className="form-group">
        <label>Hotel Name</label>
        <input
          type="text"
          name="hotelName"
          value={hotelData.hotelName}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
        <label>About Hotel</label>
        <textarea
          name="aboutHotel"
          value={hotelData.aboutHotel}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
        <label>Address</label>
        <input
          type="text"
          name="address"
          value={hotelData.address}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
        <label>Contact No</label>
        <input
          type="text"
          name="contactNo"
          value={hotelData.contactNo}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
            <label className="input-label" htmlFor="hotelType">Hotel Type</label>
            <HotelType name="hotelType" value={hotelData.hotelType}/>
          </div>

      <div className="form-group">
        <label>Hotel Amenities</label>
        <input
          type="text"
          name="hotelAmenities"
          value={hotelData.hotelAmenities}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
        <label>Room Capacity</label>
        <input
          type="number"
          name="roomCapacity"
          value={hotelData.roomCapacity}
          onChange={handleChange}
        />
      </div>

      <button type="submit" className="btn btn-primary" disabled={isFetching}>
        {isFetching ? 'Updating...' : 'Update'}
      </button>
    </form>
  </div>
);

};

export default UpdateHotelForm;
