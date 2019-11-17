# Game Engine
  - ### WindowHandler
    - Initialize window using JFrame
    - Create a new canvas
    - Set up many parameters
  - ### PhysicsHandler
    - Deal with movement of GameObject
    - Horizontal and vertical distance calculation with a given timeLength(currentX += traveledDistance)
  - ### CanvasHandler
    - Initialize canvas with given height and width
    - This canvas can be used elsewhere
  - ### CollisionHandler
    - Check collision on two squares
    - There will be more

# Flappy bird
  - ### GameCore
    - The implementation of core game loop
    - Set or hold FPS, Width, Height, Title, Graphics pen, Canvas, Bufferstrategy, List of objects
  - ### GameObject
    - Set and hold many properties related to GameObject (Currently, basic positions on screen)
  - ### LocationProperties
    - Additional properties that will be stored in GameObject
    - Horizontal and vertical velocity and acceleration
  - ### MovementHandler
    - Independent thread for calculating the precise positions of all of currently existing GameObjects
  - ### Renderer
    - Independent thread for rendering all of currently existing GameObjects with specified FPS
