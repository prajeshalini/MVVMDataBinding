# MVVMDataBinding
This sample is for demo purposes to understand the MVVM architecture, completely based on [todo-mvvm-databinding](https://github.com/googlesamples/android-architecture/tree/todo-mvvm-databinding/).In this sample we are simply creating users in local db and fetching them to show as a list of users.It needs lot of improvisations but to start with simple basic steps it is good to go.

## MVVM Architecture
We can find so many excellent blogs and articles explaining what MVVM is.But here we see it in more pragmatic way what these theory are implying to.Let's have a look at below diagram.


![alt text](https://cdn-images-1.medium.com/max/800/0*5mD214cjNXU-V6lf.png)

Here, view has reference to view-model but view-model has no reference to view.As you can see in code, the view-model clases has observable fields which reflect there changes in the views through two way binding.Datamodel has abstracted the local database as datasource.You can create data source of your choice whether remote or local or both.view-model interacts with datamodel to fetch information to show on views.I think this has pretty much explained basics of this pattern rest you will explore once you start working with it.

This sample is open to suggestions and feedback.Please write back in case you find anything wrong in implementations.
