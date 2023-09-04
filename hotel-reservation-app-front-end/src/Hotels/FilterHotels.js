import HotelCard from './HotelCard';
import React from "react";
import axios from 'axios';
import { useState,useEffect } from 'react';
import { useParams } from 'react-router-dom/cjs/react-router-dom.min';



const getRandomRoomImage = () => {
  const hotelId = Math.floor(Math.random() * 1000); // Generate a random room ID
  return `https://source.unsplash.com/400x300/?hotel,deluxe-rooms,luxury-rooms/${hotelId}`;
};




const FilterHotels = () => {
    // /filter-hotel/${selectedCity}/${hotelType}
    const {selectedCity,hotelType}=useParams();
    console.log(selectedCity+" "+hotelType);
    const [data, setData] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/hotel/${selectedCity}/hotelType/${hotelType}`)
            .then(response => {
                setData(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    console.log(data);
    return (
        <div className='card-container'>{data.map(item => <HotelCard data={item} myurl={getRandomRoomImage(item.hotelId)}></HotelCard>)}</div>
    );
}

export default FilterHotels;