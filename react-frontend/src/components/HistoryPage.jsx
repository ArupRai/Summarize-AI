import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './HistoryPage.css'; // Create a CSS file for styling

function HistoryPage() {
    const [history, setHistory] = useState([]);

    useEffect(() => {
        const fetchHistory = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/history');
                await setHistory(response.data);
                await console.dir(response);
                await console.dir(response.data);
            } catch (error) {
                console.error('Error fetching history:', error);
            }
        };

        fetchHistory();
    }, []);

    return (
        <div className="history-container">
            <h1>Request History</h1>
            <div className="history-list">
                {history.map((item, index) => (
                    <div key={index} className="history-item">
                        <div className="history-date">{item.date}</div>
                        <div className="history-time">{item.time}</div>
                        <div className="history-url">{item.url}</div>
                        <div className="history-summary">{item.summary}</div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default HistoryPage;
