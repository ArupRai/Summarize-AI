import React, { useState } from 'react';
import axios from 'axios';

function SummarizePage() {
    const [url, setUrl] = useState('');
    const [summary, setSummary] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        const response = await axios.get(`/summarize?url=${url}`);
        setSummary(response.data.summary);
    };

    return (
        <div>
            <h1>Summarize Website</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={url}
                    onChange={(e) => setUrl(e.target.value)}
                    placeholder="https://cimba.ai"
                />
                <button type="submit">Summarize</button>
            </form>
            {summary && <p>{summary}</p>}
        </div>
    );
}

export default SummarizePage;
