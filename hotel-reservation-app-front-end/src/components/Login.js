import React, { useState } from 'react';
import axios from 'axios';
import './LoginForm.css'; 
//import { useNavigate } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useHistory } from 'react-router-dom';
function Login() {
  const history=useHistory();
  const [authenticated, setauthenticated] = useState(localStorage.getItem(localStorage.getItem("authenticated")|| false));
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const loginData={
    userName:username,
    password:password
  }

  console.log(loginData);

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleLogin = async () => {
    try {
      const response = await axios.post(`http://localhost:8080/authenticate/login`,loginData);
      // Assuming the response contains a token upon successful login
      const tokendata='Bearer '+response.data.token; 
      // Handle successful login (e.g., store token in localStorage)
       
      localStorage.setItem('token', tokendata);
      setauthenticated(true);
      localStorage.setItem("authenticated", true);
      localStorage.setItem("userId",response.data.userId);
      // Redirect to admin dashboard or other page
      alert("login SuccessFull ...");
      //history.goBack();
    window.location.href = '/';
    } catch (error) {
      // Handle login error (display error message)
      setError('Invalid credentials. Please try again.');
    }
  };

  return (
    <div className="login-container">
      <img src="http://tedxuniversityofluxembourg.com/2018/wp-content/uploads/2018/05/welcome-3344772_640.jpg" alt="Login" className="login-image" />
      <div className="login-form">
        <h2>Login</h2>
        <div className="input-container">
          <span className="icon" ><i className="fas fa-user"></i></span>
          <label className="input-label">Username </label>
          <input
            type="text"
            value={username}
            onChange={handleUsernameChange}
            placeholder="Enter Username"
            className='input-field'
            required
          />
        </div>
        <div className="input-container">
          <span className="icon"><i className="fas fa-lock"></i></span>
          <label className="input-label">Password </label>
          <input
            type="password"
            value={password}
            onChange={handlePasswordChange}
            placeholder="Enter Password"
            className='input-field'
            required
          />
        </div>
        
        <button type="button" onClick={handleLogin}>Login</button>
        <p>Not a user? <a href="/register">Register here</a></p>
      </div>
    </div>
  );
}

export default Login;
