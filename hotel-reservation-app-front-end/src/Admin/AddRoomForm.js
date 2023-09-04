import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Form, Container, Row, Col, Button, Card } from 'react-bootstrap';

import { useLocation } from 'react-router-dom/cjs/react-router-dom';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

function AddRoomForm() {
    const location = useLocation();
    const { hotelData } = location.state;
    const { hotelId } = useParams();
    const [roomData, setRoomData] = useState({
        hotelId: hotelId,
        roomType: "DELUXE",
        aboutRoom: "",
        roomFacilities: "",
    });
    const history= useHistory();
    const headers = {
        Authorization: localStorage.getItem('token'),
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setRoomData({ ...roomData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // POST request
            const response = await axios.post('http://localhost:8080/room/add-room', roomData, { headers });
            
            alert('Room added successfully!');

            //Clear fields after addition
            setRoomData({
                hotelId: hotelId,
                roomType: "DELUXE",
                aboutRoom: "",
                roomFacilities: "",
            });
        } catch (error) {
            console.error('Error adding room:', error);
            // Handle error, e.g., display an error message
            //alert('Error adding room. Please try again.');
        }
    };

    return (
        <Container>
            <Card className="my-4" style={{ fontWeight: 'bold', color: 'black' }}>
                <Card.Header>
                    <Card.Title style={{ fontSize: '30px', fontWeight: 'bolder', color: 'royalblue' }}>Add New Room For {hotelData.hotelName}</Card.Title>
                    <Card.Subtitle className="mb-4 text-muted" style={{ fontWeight: 'bold', color: 'black' }}>
                        Hotel Address: {hotelData.address.streetAddress}, {hotelData.address.city}
                    </Card.Subtitle>
                    <Card.Text style={{ fontWeight: 'bold', color: 'black' }}> {hotelData.address.state} </Card.Text>
                    <Card.Text style={{ fontWeight: 'bold', color: 'black' }}>
                        Contact Number: {hotelData.contactNo}
                    </Card.Text>
                </Card.Header>
                <Card.Body>
                    <Card.Text style={{ fontWeight: 'bold', color: 'red' }}>(Fields marked with * are mandatory)</Card.Text>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group className="mb-3">
                            <Form.Label>Room Type</Form.Label>
                            <Form.Select
                                name="roomType"
                                value={roomData.roomType}
                                onChange={handleChange}
                                required
                            >
                                <option value="DELUXE">DELUXE</option>
                                <option value="SUITE">SUITE</option>
                                <option value="DOUBLE_ROOM">DOUBLE_ROOM</option>
                                <option value="CLASSIC">CLASSIC</option>
                                <option value="LUXURY">LUXURY</option>
                                <option value="SINGLE">SINGLE</option>
                                <option value="STUDIO">STUDIO</option>
                                <option value="PRESIDENTIAL_SUITE">PRESIDENTIAL SUITE</option>
                                <option value="VILLA">VILLA</option>
                            </Form.Select>

                        </Form.Group>

                        <Form.Group className="mb-3">
                            <Form.Label>Room Description  </Form.Label><label style={{ color: "red" }}> *</label>
                            <Form.Control
                                as="textarea"
                                name="aboutRoom"
                                value={roomData.aboutRoom}
                                onChange={handleChange}
                                rows={3}
                                required
                            />
                        </Form.Group>

                        <Form.Group className="mb-3">
                            <Form.Label>Room Facilities  </Form.Label><label style={{ color: "red" }}> *</label>
                            <Form.Control
                                type="text"
                                name="roomFacilities"
                                value={roomData.roomFacilities}
                                onChange={handleChange}
                                required
                            />
                        </Form.Group>

                        <Button variant="success" type="submit">
                            Submit
                        </Button>
                        <Button onClick={()=>history.push('/hotel-list-admin')}>Go Back
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        </Container>
    );
}

export default AddRoomForm;
