using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


public class GameMaster : MonoBehaviour {
    public GameObject playerRef;
    public GameObject hammerRef;
    public GameObject capRef;
    public GameObject basketRef;
    public GameObject racketRef;
    public GameObject topWallRef;


    public static int bestTime;

    public int Level;
    public int amntOfAnnoyingGuys;

    public AudioClip clipChearing;
    public AudioClip clipWaves;
    public AudioClip clipBallGameSong;
    public AudioClip clipWhistle;
    public AudioClip clipTennisCourt;


    private AudioSource audioChearing;
    private AudioSource audioWaves;
    private AudioSource audioBallGameSong;
    private AudioSource audioWhistle;
    private AudioSource audioTennisCourt;

    public bool levelStarted;
    public bool gameOver;
    public bool showRules;

    public Text TimeTotal;
    public Text TimeLevel;
    public Text MassText;
    public Text BestTimeText;

    public GameObject RulesPanel;
    public Text RulesText;

    public GameObject[] annoyingThings;

    public float[] thingLifeTimes = new float[25];

    public static float timeCounter;
    public float timeLeft;
 

    public static int secondsTotal;
    private int secondsLevel;
  


    // Use this for initialization
    void Start () {
      
        //bestTime = 0;
        annoyingThings = new GameObject[25];

        timeLeft = 30;
        amntOfAnnoyingGuys = Level * 5;
        gameOver = false;

        playBackgroundSound();
        CreateAnnoyingThings();
    
    }
	
	// Update is called once per frame
	void Update () {

        if (!gameOver)
        {
            if (!showRules)
            {
                timeCounter += Time.deltaTime;
                secondsTotal = Mathf.RoundToInt(timeCounter);

                timeLeft -= Time.deltaTime;
                secondsLevel = Mathf.RoundToInt(timeLeft % 60);

                updateText();

                moveAnnoyingThings();
                addTimeToAnnoyingThings();
                refreshAnnoyingThings();


            }

            if (Input.GetKey(KeyCode.G) && (showRules))
            {
                levelStarted = true;
            }
        }

        updateText();
        checkLevelStatus();
    }

    public void Awake()
    {
        audioChearing = AddAudio(clipChearing, true, true, 0.05f);
        audioWaves = AddAudio(clipWaves, true, false, .07f);
        audioBallGameSong = AddAudio(clipBallGameSong, true, false, 0.05f);
        audioWhistle = AddAudio(clipWhistle, false, false, .06f);
        audioTennisCourt = AddAudio(clipTennisCourt, true, false, .05f);
       

    }

    void checkAnnoyingThingsPosition()
    {
        for(int i = 0; i < amntOfAnnoyingGuys; i++)
        {
            //check bounds of the annoyingthings
            if(annoyingThings[i].transform.position.x > 5f || annoyingThings[i].transform.position.x < -5f || 
                annoyingThings[i].transform.position.z > 5f || annoyingThings[i].transform.position.z < -5f ||
                annoyingThings[i].transform.position.y < 0)
            {
                randomPostionForThings(annoyingThings[i]);
            }
        }
    }


    void CreateAnnoyingThings()
    {
        if (Level == 1)
        {
            //bounds are -4f, x, -4f to 4f, x, 4f
            for (int i = 0; i < amntOfAnnoyingGuys; i++)
            {
                annoyingThings[i] = Instantiate(basketRef) as GameObject;
                //in random positions
                randomPostionForThings(annoyingThings[i]);
                thingLifeTimes[i] = 0f;
            }
        }

        if (Level == 2)
        {
            for (int i = 0; i < amntOfAnnoyingGuys; i++)
            {
                annoyingThings[i] = Instantiate(capRef) as GameObject;
                //in random positions
                randomPostionForThings(annoyingThings[i]);
                thingLifeTimes[i] = 0f;
            }
        }

        if (Level == 3)
        {
            for (int i = 0; i < amntOfAnnoyingGuys; i++)
            {
                annoyingThings[i] = Instantiate(racketRef) as GameObject;
                //in random positions
                randomPostionForThings(annoyingThings[i]);
                thingLifeTimes[i] = 0f;
            }
        }

        if (Level == 4)
        {
            for (int i = 0; i < amntOfAnnoyingGuys; i++)
            {
                annoyingThings[i] = Instantiate(hammerRef) as GameObject;
                //in random positions
                randomPostionForThings(annoyingThings[i]);
                thingLifeTimes[i] = 0f;
            }
        }
    }

    void moveAnnoyingThings()
    {
        if(Level == 1)
        {
            for (int i = 0; i < amntOfAnnoyingGuys; i++)
            {
            //annoyingThings[i].transform.Rotate(new Vector3(0, 0, 45) * Time.deltaTime);
                if (annoyingThings[i].transform.position.y < 5f)
                {
                    Vector3 forceTowardPlayer = playerRef.transform.position - annoyingThings[i].transform.position;
                    annoyingThings[i].GetComponent<Rigidbody>().AddForce(forceTowardPlayer / 5);
                }   
            }
        }

        if (Level == 2)
        {
            for (int i = 0; i < amntOfAnnoyingGuys; i++)
            {
                //annoyingThings[i].transform.Rotate(new Vector3(0, 0, 45) * Time.deltaTime);
                if (annoyingThings[i].transform.position.y < 1f)
                {
                    Vector3 forceTowardPlayer = playerRef.transform.position - annoyingThings[i].transform.position;
                    annoyingThings[i].GetComponent<Rigidbody>().AddForce(forceTowardPlayer / 3);
                }
            }
        }

        if (Level == 3)
        {
            for (int i = 0; i < amntOfAnnoyingGuys; i++)
            {
                //annoyingThings[i].transform.Rotate(new Vector3(0, 0, 45) * Time.deltaTime);
                if (annoyingThings[i].transform.position.y < 1f)
                {
                    Vector3 forceTowardPlayer = playerRef.transform.position - annoyingThings[i].transform.position;
                    annoyingThings[i].GetComponent<Rigidbody>().AddForce(forceTowardPlayer / 2);
                }
            }
        }

        if (Level == 4)
        {
            for (int i = 0; i < amntOfAnnoyingGuys; i++)
            {
                //annoyingThings[i].transform.Rotate(new Vector3(0, 0, 45) * Time.deltaTime);
                if (annoyingThings[i].transform.position.y < 1f)
                {
                    Vector3 forceTowardPlayer = playerRef.transform.position - annoyingThings[i].transform.position;
                    annoyingThings[i].GetComponent<Rigidbody>().AddForce(forceTowardPlayer / 2);
                }
            }
        }
    }


    void updateText()
    {
        if (!showRules)
        {
            TimeTotal.text = "Total time: " + secondsTotal;
            TimeLevel.text = "Time left: " + secondsLevel;
            MassText.text = "Air: " + GameObject.Find("Player").GetComponent<PlayerController>().air;
            BestTimeText.text = "Best time: " + bestTime;
        } else
        {
            RulesText.text = "Level " + (Level + 1) +"\n Press 'G' to start level";
        }
    }


    void refreshAnnoyingThings()
    {
        for (int i = 0; i < amntOfAnnoyingGuys; i++)
        {
            if (thingLifeTimes[i] > 5f)
            {
                randomPostionForThings(annoyingThings[i]);
                thingLifeTimes[i] = 0f;
            }

        }
        
    }

    public AudioSource AddAudio(AudioClip clip, bool loop, bool playAwake, float vol)
    {

        AudioSource newAudio = gameObject.AddComponent<AudioSource>();

        newAudio.clip = clip;
        newAudio.loop = loop;
        newAudio.playOnAwake = playAwake;
        newAudio.volume = vol;

        return newAudio;

    }

    void addTimeToAnnoyingThings() {
        for (int i = 0; i < amntOfAnnoyingGuys; i++)
        {
            thingLifeTimes[i] += Time.deltaTime;
        }
    }

    void randomPostionForThings(GameObject thing)
    {
        if(Level == 1)
            thing.transform.position = new Vector3(Random.RandomRange(-3.5f, 3.5f), Random.RandomRange(13f, 17f), Random.RandomRange(-3.5f, 3.5f));
        if(Level == 2)
            thing.transform.position = new Vector3(Random.RandomRange(-4.5f, 4.5f), Random.RandomRange(13f, 17f), Random.RandomRange(-4.5f, 4.5f));
        if (Level == 3)
            thing.transform.position = new Vector3(Random.RandomRange(-4.5f, 4.5f), Random.RandomRange(13f, 17f), Random.RandomRange(-4.5f, 4.5f));
        if (Level == 4)
            thing.transform.position = new Vector3(Random.RandomRange(-4.5f, 4.5f), Random.RandomRange(13f, 17f), Random.RandomRange(-4.5f, 4.5f));
    }

    void playBackgroundSound()
    {
        if (Level == 1)
            audioChearing.Play();
        if (Level == 2)
            audioBallGameSong.Play();
        if (Level == 3)
            audioTennisCourt.Play();
        if (Level == 4)
            audioWaves.Play();
    }

    void checkLevelStatus()
    {
        if (timeLeft <= 0 && !showRules)
        {
            if(Level < 4)
            {
                audioWhistle.Play();
                showRules = true;
                levelStarted = false;

                TimeLevel.enabled = false;
                MassText.enabled = false;
                BestTimeText.enabled = false;
                topWallRef.SetActive(true);
                RulesPanel.SetActive(true);
            } else
            {
                gameOver = true;
            }
    
        }

        if (showRules && levelStarted)
        {
            TimeLevel.enabled = true;
            MassText.enabled = true;
            BestTimeText.enabled = true;
            topWallRef.SetActive(false);
            RulesPanel.SetActive(false);
            timeLeft = 30;
            Level++;
            SceneManager.LoadScene("Level " + Level);
            showRules = false;
        }

        if (gameOver)
        {
            if(secondsTotal > bestTime)
            {
                bestTime = secondsTotal;
            }
            audioWhistle.Play();
            SceneManager.LoadScene("Game Over");
        }
     
    }
}
