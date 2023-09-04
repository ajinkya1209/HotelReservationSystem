import React, { useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useHistory, useLocation } from 'react-router-dom/cjs/react-router-dom';
// import HotelSearch from './HotelSearch';

    const DeleteParticularRoom = () => {
      const headers = {
        Authorization: localStorage.getItem('token'),
      };
          const location = useLocation();
          const { ...hotelData} = location.state;
          console.log("hotelData in deleteParticular");
          console.log(hotelData);
    const { roomId } = useParams();
    const {hotelId}=hotelData.hotelId;
    const history=useHistory();
    console.log('roomId'+roomId);
    console.log(headers);
    
    useEffect(() => {
        // Call handleDelete when the component mounts
        handleDelete();
      }, []);
    const handleDelete = async () => {
    // Make an Axios DELETE request to delete the room with the specified roomId
    await axios.delete(`http://localhost:8080/room/${roomId}` ,{ headers })
      .then((response) => {
        // Handle success, e.g., show a success message or navigate back to the previous page
        alert('Room deleted successfully');
        history.push(`/get-room-list/${hotelId}`, { hotelId: hotelId, hotelData:hotelData});
        console.log(response.data);
      })
      .catch((error) => {
        // Handle error, e.g., show an error message
        //alert('Error deleting room:', error);
        history.push(`/get-room-list/${hotelId}`, { hotelId: hotelId, hotelData:hotelData });
      });
  };

  return (
    <div>
        {/* <button onClick={() => history.goBack()}>Cancel</button> */}
    </div>
  );
};

export default DeleteParticularRoom;
