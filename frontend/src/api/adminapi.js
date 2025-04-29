// const BASE_URL = "http://localhost:8080/api/v1";
const BASE_URL = "https://web-tech-project.azurewebsites.net/api/v1";

function tokenProcess() {
    return localStorage.getItem("token");
}


const headerPost = {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${tokenProcess()}`
    }
}

const headerPut = {
    method: "PUT",
    headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${tokenProcess()}`
    }
}

const headerDelete = {
    method: "DELETE",
    headers: {
        "Authorization": `Bearer ${tokenProcess()}`
    }
}

const headerGet = {
    headers: {
        "Authorization": `Bearer ${tokenProcess()}`
    }
}


/**
 *
 * @param {String} username
 * @param {*} password
 * @returns
 */

const headers = new Headers();
const login = async (username, password) => {
    headers.set("Authorization", `Basic ${btoa(username + ":" + password)}`);

    try {
        const res = await fetch(BASE_URL + "/login", {
            method: "POST",
            headers: headers
        });
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }
        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}


// ************************************************************
//  GAME SCHEDULES
// ************************************************************

/**
 *
 *
 * @returns {Promise<Array>} all the schedules
 * * */
const gameSchedule = async () => {
    try {
        const res = await fetch(BASE_URL + "/gameSchedule", headerGet);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/**
 * @returns {Promise<Object>}
 * @param {number} id
 */
const gameScheduleById = async (id) => {
    try {
        const res = await fetch(BASE_URL + `/gameSchedule/${id}`, headerGet);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}


/**
 *
 * @param {*} schedule
 * @returns
 */
const addGameSchedule = async (schedule) => {
    try {
        const res = await fetch(BASE_URL + "/gameSchedule",
            {
                ...headerPost,
                body: JSON.stringify(schedule)
            }
        )

        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/**
 *
 * @param {*} schedule
 * @returns
 */
const editGameSchedule = async (schedule) => {
    try {
        const res = await fetch(BASE_URL + "/gameSchedule",
            headerPut,
            headerPut[body](Json.Stringify(schedule))
            // if it does not work, put:
            // change back to origin.
        )
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}


/**
 *
 * @param {*} id
 * @returns
 */
const deleteGameSchedule = async (id) => {
    try {
        const res = await fetch(BASE_URL + `/gameSchedule/${id}`,
            headerDelete
        )
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}
// ************************************************************
//  GAMES
// ************************************************************

/**
 *
 * @param {Number} scheduleId
 * @returns {Promise<Array>} all the games
 */
const findAllGames = async (scheduleId) => {
    try {
        const url = BASE_URL + `/gameSchedule/${scheduleId}/games`;
        const res = await fetch(url, headerGet);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/***
 * @param {Number} scheduleId
 * @param {Number} gameId
 * @returns {Promise<Object>} the game
 */
const findGameById = async (scheduleId, gameId) => {
    try {
        const url = BASE_URL + `/gameSchedule/${scheduleId}/games/${gameId}`;
        const res = await fetch(url, headerGet);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/**
 *
 * @param {Number} scheduleId
 * @param {*} game
 * @returns
 */
const addNewGame = async (scheduleId, game) => {
    try {
        const url = BASE_URL + `/gameSchedule/${scheduleId}/games`;
        const res = await fetch(url,
            {
                ...headerPost,
                body: JSON.stringify(game)
            }
        )
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}


/**
 *
 * @param {Number} scheduleId
 * @param {*} game
 * @returns
 */
const editGame = async (scheduleId, game) => {
    try {
        const url = BASE_URL + `/gameSchedule/${scheduleId}/games/${game.id}`;
        const res = await fetch(url, headerPut, headerPut[body](Json.Stringify(game)));
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/**
 *
 * @param {Number} scheduleId
 * @param {Number} gameId
 * @returns
 */
const deleteGame = async (scheduleId, gameId) => {
    try {
        const url = BASE_URL + `/gameSchedule/${scheduleId}/games/${gameId}`;
        const res = await fetch(url, {
            method: "DELETE",
        })
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/**
 *
 * @param {String} emails
 * @returns
 */
const invite = async (emails) => {
    try {
        const url = BASE_URL + "/invite";
        const res = await fetch(url, {
            ...headerPost,
            body: JSON.stringify(emails),
        })
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/**
 *
 * @returns {Promise<Array>} all the crew
 * */

const findAllCrew = async () => {
    try {
        const url = BASE_URL + "/crewMember";
        const res = await fetch(url, headerGet);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/***
 * @param {Number} id
 * @returns {Promise<Object>} the crew
 * */

const findCrewById = async (id) => {
    try {
        const url = BASE_URL + `/crewMember/${id}`;
        const res = await fetch(url);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/**
 * Fetches all the users under a game
 * @param {Number} gameId - The id of the game
 * @returns {Promise<Array>} - The response from the API
 */

const usersUnderGame = async (gameId) => {
    try {
        // const url = BASE_URL + `/crewList/${gameId}`;
        const url = BASE_URL + `/crewSchedule/${gameId}`;
        const res = await fetch(url, headerGet);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }
        return res.json();
    } catch (err) {
        console.log(err);
    }
}

export default {
    login,
    gameSchedule,
    gameScheduleById,
    addGameSchedule,
    editGameSchedule,
    deleteGameSchedule,
    findAllGames,
    findGameById,
    addNewGame,
    editGame,
    deleteGame,
    invite,
    findAllCrew,
    findCrewById,
    usersUnderGame
}