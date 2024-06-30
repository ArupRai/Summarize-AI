import axios from 'axios';
import React, { useState } from 'react';

const SummarizeComponent = () => {
  const [summary, setSummary] = useState('');
  const [text, setText] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent default form submission

    try {
      const response = await axios.post(
        'http://localhost:8080/api/summarize',
        { text },
        {
          timeout: 60000, // Set timeout to 60 seconds
          headers: {
            'Content-Type': 'application/json'
          }
        }
      );

      if (response.status === 200) {
        await setSummary(response.data); // Adjust this according to your API response structure
        setError('');
      } else {
        setError('Network response was not ok');
      }
    } catch (error) {
      if (error.code === 'ECONNABORTED') {
        setError('Request timed out');
      } else {
        setError('Failed to fetch data. Please try again later.');
      }
      // console.error('Error:', error);
    }
  };

  return (
    <div>
      <textarea
        value={text}
        onChange={(e) => setText(e.target.value)}
        placeholder="Enter text to summarize"
      />
      <button onClick={handleSubmit}>Summarize</button>
      {summary && <div><p style={{ color: 'green' }}>Summary: {summary}</p></div>}
      {error && <div><p style={{ color: 'red' }}>{error}</p></div>}
    </div>
  );
};

export default SummarizeComponent;
