# "Tell, Don't Ask" Exercise

> Forked in August 2021 from https://github.com/jitterted/tell-dont-ask-exercise to share for a collaborative work session on this exercise.
> 
> This was forked from https://github.com/racingDeveloper/tell-dont-ask-kata -- [@racingDeveloper](https://twitter.com/racingDeveloper) on Twitter.
> 
> I've updated JUnit to the JUnit 5, and replaced Hamcrest, etc. with AssertJ assertions.
> 
> Also replaced references to "kata" to be "exercise".

A legacy refactor exercise, focused on the violation of the [tell don't ask](https://toolshed.com/articles/1998-07-01-TellDontAsk.html) principle and the [anemic domain model](https://martinfowler.com/bliki/AnemicDomainModel.html).

## Instructions
Here you find a simple order flow application. It's able to create orders, do some calculations (totals and taxes), and manage them (approve/reject and ship).

The old development team did not find the time to build a proper domain model, but instead preferred to use a procedural style, building this anemic domain model.
Fortunately, they did at least take the time to write unit tests for the code.

Your new CTO, after many bugs caused by this application, asked you to refactor this code to make it more maintainable and reliable.

## What to focus on
As the title of the exercise says, of course, the _tell don't ask_ principle.
You should be able to remove all the setters moving the behavior into the domain objects (_Feature Envy_ perhaps?).

But don't stop there.

If you can remove some test cases because they don't make sense anymore (e.g.: you cannot compile the code to do the wrong thing) feel free to do it!

## Contribute
If you would like to contribute to this exercise adding new cases or smells: please open a pull request!

## Feedback
Feedback is welcome!

How did you find the exercise? Did you learn anything from it?

Please contact me on Twitter [@JitterTed](https://twitter.com/jitterted) or use the GitHub repo wiki!
