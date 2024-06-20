import React, { useEffect, useState } from 'react';
import axios from 'axios';

function HistoryPage() {
    const [history, setHistory] = useState([]);

    useEffect(() => {
        const fetchHistory = async () => {
            const response = await axios.get('/history');
            setHistory(response.data);
        };

        fetchHistory();
    }, []);

    return (
        <div>
            <h1>Request History</h1>
            <ul>
                {history.map((item, index) => (
                    <li key={index}>{item.url}: {item.summary}</li>
                ))}
            </ul>
        </div>
    );
}

export default HistoryPage;
