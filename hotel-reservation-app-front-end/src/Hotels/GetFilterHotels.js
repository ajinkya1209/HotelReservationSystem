import React, { useState, useEffect } from 'react';
import axios from 'axios';
import  './HotelCard.css';
import HotelCard from './HotelCard';
const getRandomRoomImage = () => {
  const hotelId = Math.floor(Math.random() * 1000); // Generate a random room ID
  return `https://source.unsplash.com/400x300/?hotel,deluxe-rooms,luxury-rooms/${hotelId}`;
};


function HotelList() {
  const [data, setData] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/hotel/get-hotel')
      .then(response => {
        setData(response.data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []);

  
  console.log(data);
  return (
    <div className='card-container'>{data.map(item => <HotelCard data={item} myurl={getRandomRoomImage(item.hotelId)}></HotelCard> )}</div>
  );
}

export default HotelList;
