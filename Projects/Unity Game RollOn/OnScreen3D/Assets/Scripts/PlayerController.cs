using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PlayerController : MonoBehaviour {


    public Material BasketballMat;
    public Material BaseballMat;
    public Material TennisballMat;
    public Material BeachballMat;

    public AudioClip clipChearing;
    public AudioClip clipBathit;
    public AudioClip clipHammer;
    public AudioClip clipTennisScrap;

    public float jumpForce;


    private AudioSource audioChearing;
    private AudioSource audioBathit;
    private AudioSource audioHammer;
    private AudioSource audioTennisScrap;


    public float air;
    public bool airRemaining;
    public float speed;
    public int Level;

    private Rigidbody rb;
    private int count;

    public void Awake()
    {

        //ADD MORE SOUNDS HERE
        audioBathit = AddAudio(clipBathit, false, false, 0.4f);
        audioHammer = AddAudio(clipHammer, false, false, 0.4f);
        audioChearing = AddAudio(clipChearing, false, false, 0.055f);
        audioTennisScrap = AddAudio(clipTennisScrap, false, false, 0.2f);

    }

    void Start()

    {
        rb = GetComponent<Rigidbody>();
        count = 0;
        air = 1f;
        airRemaining = true;
    }

    void Update()
    {

        jumpForYourLife();
        if (!airRemaining)
        {
            air = 0;
        }
     
    }


    private void FixedUpdate()
    {
        float moveHorizontal = Input.GetAxis("Horizontal");
        float moveVertical = Input.GetAxis("Vertical");

        Vector3 movement = new Vector3(moveHorizontal, 0, moveVertical);

        rb.AddForce(movement * speed);

    }

    
    void OnCollisionEnter(Collision other)
    {
        if (other.collider.tag == "BasketballNet"){

            addTimeToGameClock();
            respawnObjects(other.gameObject);
            
            audioChearing.Play();
            
        }

        if (other.collider.tag == "Cap")
        {
            addTimeToGameClock();
            respawnObjects(other.gameObject);
            audioBathit.Play();
           
        }

        if (other.collider.tag == "Racket")
        {
            addTimeToGameClock();
            respawnObjects(other.gameObject);
            audioTennisScrap.Play();

        }

        if(other.collider.tag == "Hammer")
        {
            addTimeToGameClock();
            respawnObjects(other.gameObject);
            audioHammer.Play();
        }
    }

    void respawnObjects(GameObject theObject)
    {
        theObject.gameObject.transform.position = new Vector3(1f, 15f, 1f);
        for (int i = 0; i < GameObject.Find("GM").GetComponent<GameMaster>().amntOfAnnoyingGuys; i++)
        {
            if (theObject.gameObject.Equals(GameObject.Find("GM").GetComponent<GameMaster>().annoyingThings[i]))
            {
                GameObject.Find("GM").GetComponent<GameMaster>().thingLifeTimes[i] = 0f;
            }
        }
    }


    void addTimeToGameClock()
    {
        if (GameObject.Find("GM").GetComponent<GameMaster>().timeLeft <= 30)
            GameObject.Find("GM").GetComponent<GameMaster>().timeLeft += 1;
        else
            GameObject.Find("GM").GetComponent<GameMaster>().timeLeft = 30;

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

    void jumpForYourLife()
    {
        if (Input.GetKey(KeyCode.Space) && (airRemaining))
        {
                air -= .03f;
                rb.AddForce(Vector3.up * jumpForce);
                if (air <= 0f)
                    airRemaining = false;
        }
    }

}

