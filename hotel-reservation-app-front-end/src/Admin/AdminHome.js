import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { Form, Button, Container } from 'react-bootstrap';
import axios from 'axios';

const AdminLoginForm = () => {
  const [userName, setuserName] = useState('');
  const [password, setPassword] = useState('');
  //const [authenticated, setAuthenticated] = useState('');
  const history = useHistory();

  const handleEmailChange = (e) => {
    setuserName(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      
     
      const response = await axios.post('http://localhost:8080/authenticate/login', {
        userName,
        password,
      });

      
      const token = response.data.token;
      
      localStorage.setItem('token','Bearer '+ token);
      localStorage.setItem('authenticated',true);

      // Redirect to HotelListAdmin.js
      if(localStorage.getItem('authenticated')==='true')
      history.push('/hotel-list-admin');
    } catch (error) {
      console.error('Login failed:', error);
      alert("Invalid credentials");
     
    }
  };

  return (
    <Container>
      <h2>Admin Login</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="email">
          <Form.Label>Email:</Form.Label><label style={{color:"red"}}> *</label>
          <Form.Control
            type="email"
            value={userName}
            onChange={handleEmailChange}
            required
          />
        </Form.Group>

        <Form.Group controlId="password">
          <Form.Label>Password:</Form.Label><label style={{color:"red"}}> *</label>
          <Form.Control
            type="password"
            value={password}
            onChange={handlePasswordChange}
            required
          />
        </Form.Group>

        <Button variant="primary" type="submit">
          Login
        </Button>
      </Form>
    </Container>
  );
};

export default AdminLoginForm;
